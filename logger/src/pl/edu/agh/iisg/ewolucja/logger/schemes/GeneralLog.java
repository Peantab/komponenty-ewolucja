package pl.edu.agh.iisg.ewolucja.logger.schemes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GeneralLog extends AbstractLog {
    private long runId;
    private String caller;
    private Map<String, String> providedFields;

    public GeneralLog(long runId, String caller, String json){
        this.runId = runId;
        this.caller = caller;
        Gson gson = new Gson();
        Type stringToStringMap = new TypeToken<Map<String, String>>() {}.getType();
        providedFields = gson.fromJson(json, stringToStringMap);
    }

    @Override
    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>(providedFields);
        map.put(RUN_ID, String.valueOf(runId));
        map.put(CALLER, caller);
        return map;
    }

    @Override
    public String getIndexName() {
        return "generalLogs";
    }
}
