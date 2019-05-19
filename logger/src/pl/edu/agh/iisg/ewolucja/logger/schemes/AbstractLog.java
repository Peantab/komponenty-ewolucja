package pl.edu.agh.iisg.ewolucja.logger.schemes;

public abstract class AbstractLog {
    public abstract String getJson();
    public abstract String getIndexName();

    final String RUN_ID = "\"run_id\"";
    final String CALLER = "\"caller\"";
}
