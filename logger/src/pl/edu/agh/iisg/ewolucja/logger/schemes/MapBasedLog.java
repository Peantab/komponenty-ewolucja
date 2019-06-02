package pl.edu.agh.iisg.ewolucja.logger.schemes;

import java.util.Map;

public class MapBasedLog extends AbstractLog{
    private long runId;
    private String caller;
    private Map<String, String> entries;

    public MapBasedLog(long runId, String caller, Map<String, String> entries){
        this.runId = runId;
        this.caller = caller;
        this.entries = entries;
        generateTimestamp();
    }

    @Override
    public String getJson() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{\n");

        stringBuilder.append(RUN_ID);
        stringBuilder.append(": \"");
        stringBuilder.append(runId);
        stringBuilder.append("\",\n");

        stringBuilder.append(CALLER);
        stringBuilder.append(": \"");
        stringBuilder.append(caller);
        stringBuilder.append("\",\n");

        stringBuilder.append(TIMESTAMP);
        stringBuilder.append(": \"");
        stringBuilder.append(timestamp);
        stringBuilder.append("\"");

        for (Map.Entry<String, String> entry: entries.entrySet()) {
            stringBuilder.append(",\n");
            stringBuilder.append("\"").append(entry.getKey()).append("\": \"");
            stringBuilder.append(entry.getValue()).append("\"");
        }

        stringBuilder.append("\n}\n");

        return stringBuilder.toString();
    }

    @Override
    public String getIndexName() {
        return "general";
    }
}
