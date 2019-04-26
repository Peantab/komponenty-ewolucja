package pl.edu.agh.iisg.ewolucja.core;

import java.util.Map;

public class Configuration {

    private Map<String, Object> parameters;

    public Configuration(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Object getParameter(String name) {
        return parameters.get(name);
    }
}
