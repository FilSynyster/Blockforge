package agenda.database.sql;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SQLFileManager {

    private final Path sqlDirectory;

    public static SQLFileManager createDefault() {
        return new SQLFileManager(SQLFileManager.class.getClassLoader().getResource("sql"));
    }

    public SQLFileManager(URL sqlDirectory) {
        try {
            this.sqlDirectory = Paths.get(sqlDirectory.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("BlockForge says: inválid SQL directory: " + sqlDirectory, e);
        }
    }

    public Map<Class<?>, SQLDocument> load() {
        if (!Files.isDirectory(sqlDirectory)) {
            throw new IllegalStateException("Diretório de SQL não encontrado: " + sqlDirectory);
        }

        try (var files = Files.list(sqlDirectory)) {

            return files
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".xml"))
                    .map(this::parse)
                    .collect(Collectors.toMap(
                            document -> loadClass(document.getRepositoryClass()),
                            Function.identity()
                    ));

        } catch (IOException e) {
            throw new RuntimeException("Erro ao listar diretório de SQL: " + sqlDirectory, e);
        }
    }

    private Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(
                    "BlockForge says: Repository not found: " + className, e);
        }
    }

    private SQLDocument parse(Path file) {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            // Evita XXE
            factory.setFeature(
                    "http://apache.org/xml/features/disallow-doctype-decl",
                    true
            );

            var builder = factory.newDocumentBuilder();
            Document document = builder.parse(file.toFile());
            Element root = document.getDocumentElement();

            if (!"sql".equals(root.getTagName())) {
                throw new IllegalArgumentException("Invalid root element in: " + file);
            }

            String repository = root.getAttribute("class");
            if (repository == null || repository.isBlank()) {
                throw new IllegalArgumentException("Attribute 'class' not provided in: " + file);
            }

            NodeList queryNodes = root.getElementsByTagName("query");
            Map<String, String> scriptMap = new HashMap<>();

            for (int i = 0; i < queryNodes.getLength(); i++) {
                Element queryElement = (Element) queryNodes.item(i);
                String bind = queryElement.getAttribute("bind");
                if (bind == null || bind.isBlank()) {
                    throw new IllegalArgumentException("The 'bind' attribute is not provided in: " + file);
                }
                String sql = queryElement.getTextContent().trim();
                if (sql.isBlank()) {
                    throw new IllegalArgumentException("Empty SQL for '" + bind + "' in: " + file);
                }
                scriptMap.put(bind, sql);
            }

            return new SQLDocument(repository, file, scriptMap);

        } catch (Exception e) {
            throw new RuntimeException("BlockForge says: Error processing SQL file.: " + file, e);
        }
    }

}