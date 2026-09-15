package agenda.database.ormlite.configuration;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class PersistenceConfig {

    private final Map<String, ContextConfig> contextConfigs = new LinkedHashMap<>();

    public PersistenceConfig(String file) {
        load(file);
    }

    public Map<String, ContextConfig> getContextConfigs() {
        return contextConfigs;
    }

    public ContextConfig getContextConfig(String name) {
        return contextConfigs.get(name);
    }

    private void load(String file) {
        try {
            InputStream input = PersistenceConfig.class.getClassLoader().getResourceAsStream(file);
            if (input == null) {
                throw new FileNotFoundException("Arquivo não encontrado no classpath: " + file);
            }
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(input);
            document.getDocumentElement().normalize();
            NodeList contexts = document.getElementsByTagName(ConfigFileProperties.CONTEXT_TAG);

            for (int i = 0; i < contexts.getLength(); i++) {

                Element context = (Element) contexts.item(i);
                String contextName = context.getAttribute(ConfigFileProperties.CONTEXT_NAME_PROPERTY);
                ContextConfig config = new ContextConfig(contextName);
                NodeList properties = context.getChildNodes();

                for (int j = 0; j < properties.getLength(); j++) {

                    if (!(properties.item(j) instanceof Element property)) {
                        continue;
                    }
                    String name = property.getTagName();
                    String value = property.getAttribute(ConfigFileProperties.VALUE_PROPERTY);
                    config.put(name, value);
                }

                contextConfigs.put(contextName, config);
            }

        } catch (Exception e) {
            throw new RuntimeException("Não foi possível carregar a configuração: " + file, e);
        }
    }
}
