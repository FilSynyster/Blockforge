package agenda.database.ormlite.configuration;

import java.util.LinkedHashMap;
import java.util.Map;

public class ContextConfig {

    private final String unitName;

    private final Map<String, String> properties = new LinkedHashMap<>();

    public ContextConfig(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return unitName;
    }

    public String get(String name) {
        return properties.get(name);
    }

    public void put(String name, String value) {
        properties.put(name, value);
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("ContextConfig:{\n");

        builder.append("  \"unit-name\": \"").append(unitName).append("\"");

        for (Map.Entry<String, String> entry : properties.entrySet()) {

            builder.append(",\n")
                    .append("  \"")
                    .append(entry.getKey())
                    .append("\": \"")
                    .append(entry.getValue())
                    .append("\"");
        }

        builder.append("\n}");

        return builder.toString();
    }
}
