package pl.edu.agh.iisg.ewolucja.logger.schemes;

import java.util.Map;

public class GeneralLog extends AbstractLog {
    private long runId;
    private String caller;
    private Map<String, String> providedFields;

    public GeneralLog(long runId, String caller, String json){
        this.runId = runId;
        this.caller = caller;
//        Gson gson = new Gson();
//        Type stringToStringMap = new TypeToken<Map<String, String>>() {}.getType();
//        providedFields = gson.fromJson(json, stringToStringMap);
    }

    @Override
    public String getJson() {
//        Map<String, String> map = new HashMap<>(providedFields);
//        map.put(RUN_ID, String.valueOf(runId));
//        map.put(CALLER, caller);
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public String getIndexName() {
        return "general";
    }
}
