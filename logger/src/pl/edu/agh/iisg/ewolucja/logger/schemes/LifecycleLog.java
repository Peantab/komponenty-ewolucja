package pl.edu.agh.iisg.ewolucja.logger.schemes;

public class LifecycleLog extends AbstractLog {
    private long runId;
    private String caller;
    private LifecycleState newState;

    private final String NEW_STATE = "\"newState\"";

    public LifecycleLog(long runId, String caller, LifecycleState newState){
        this.runId = runId;
        this.caller = caller;
        this.newState = newState;
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

        stringBuilder.append(NEW_STATE);
        stringBuilder.append(": ");
        stringBuilder.append(newState.getDescription());
        stringBuilder.append('\n');

        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }

    @Override
    public String getIndexName() {
        return "lifecycle";
    }

    public enum LifecycleState{
        START_WORK("\"startWork\""),
        END_WORK("\"endWork\""),
        TAKE_OVER_WORK("\"takeOverWork\"");

        private final String description;

        LifecycleState(String description){
            this.description = description;
        }

        private String getDescription(){
            return description;
        }
    }
}
