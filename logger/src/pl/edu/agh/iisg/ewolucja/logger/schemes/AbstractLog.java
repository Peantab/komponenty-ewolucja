package pl.edu.agh.iisg.ewolucja.logger.schemes;

import java.time.Instant;

public abstract class AbstractLog {
    public abstract String getJson();
    public abstract String getIndexName();

    final String RUN_ID = "\"run_id\"";
    final String CALLER = "\"caller\"";
    final String TIMESTAMP = "\"log_timestamp\"";

    long timestamp;

    void generateTimestamp(){
        this.timestamp = Instant.now().toEpochMilli();
    }
}
