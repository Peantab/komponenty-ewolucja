package pl.edu.agh.iisg.ewolucja.logger.schemes;

import java.util.Map;

public abstract class AbstractLog {
    public abstract Map<String, String> getMap();
    public abstract String getIndexName();

    final String RUN_ID = "run_id";
    final String CALLER = "caller";
}
