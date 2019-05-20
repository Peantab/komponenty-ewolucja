package pl.edu.agh.iisg.ewolucja.logger.schemes;

public class GeneralLog extends AbstractLog {
    private long runId;
    private String caller;
    private String providedJson;

    public GeneralLog(long runId, String caller, String json){
        this.runId = runId;
        this.caller = caller;
        this.providedJson = json;
        generateTimestamp();
    }

    @Override
    public String getJson() {
        String[] split = providedJson.split("\\{", 2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(split[0]);

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
        stringBuilder.append("\",\n");

        stringBuilder.append(split[1]);
        return stringBuilder.toString();
    }

    @Override
    public String getIndexName() {
        return "general";
    }
}
