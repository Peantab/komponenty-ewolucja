package pl.edu.agh.iisg.ewolucja.logger.schemes;

import java.util.HashMap;
import java.util.Map;

public class LifecycleLog extends AbstractLog {
    private long runId;
    private String caller;
    private LifecycleState newState;

    private final String NEW_STATE = "newState";

    public LifecycleLog(long runId, String caller, LifecycleState newState){
        this.runId = runId;
        this.caller = caller;
        this.newState = newState;
    }

    @Override
    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        map.put(RUN_ID, String.valueOf(runId));
        map.put(CALLER, caller);
        map.put(NEW_STATE, newState.getDescription());
        return map;
    }

    @Override
    public String getIndexName() {
        return "lifecycleLogs";
    }

    public enum LifecycleState{
        START_WORK("startWork"),
        END_WORK("endWork"),
        TAKE_OVER_WORK("takeOverWork");

        private final String description;

        LifecycleState(String description){
            this.description = description;
        }

        private String getDescription(){
            return description;
        }
    }
}
