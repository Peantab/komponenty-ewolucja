package pl.edu.agh.iisg.ewolucja.logreader;

import jdk.nashorn.api.scripting.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;


public class LogReader {
    public List<String> getLogs(int run_id, String indexName) throws IOException {
        URL url = new URL("http://localhost:9200/" + indexName + "/_search");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        OutputStream os = con.getOutputStream();

        String json = buildJson(run_id);

        os.write(json.getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();
        con.connect();
        int code = con.getResponseCode();
        if (code < 200 || code >= 300){
            con.disconnect();
            throw new RuntimeException("ElasticSearch returned code: " + code);
        }
//		FOR DEBUG:
		byte[] bajty = new byte[1000];
		con.getInputStream().read(bajty);
		printResponse(new String(bajty));
		con.disconnect();
        return null;
    }

    public String buildJson(int run_id){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{\n");
        stringBuilder.append("\"query\"");
        stringBuilder.append(": ");
        stringBuilder.append("{");
        stringBuilder.append("\"term\" :");
        stringBuilder.append("{");

        stringBuilder.append("\"run_id\"");
        stringBuilder.append(": \"");
        stringBuilder.append(run_id);
        stringBuilder.append("\"");
        stringBuilder.append("}");
        stringBuilder.append("}");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public String buildJson(int run_id, int count){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");
        stringBuilder.append("\"size\"");
        stringBuilder.append(": \"");
        stringBuilder.append(count);
        stringBuilder.append("\",");
        stringBuilder.append("\"query\"");
        stringBuilder.append(": ");
        stringBuilder.append("{");
        stringBuilder.append("\"term\" :");
        stringBuilder.append("{");

        stringBuilder.append("\"run_id\"");
        stringBuilder.append(": \"");
        stringBuilder.append(run_id);
        stringBuilder.append("\"");
        stringBuilder.append("}");
        stringBuilder.append("}");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void printResponse(String response){
        JSONObject jsonObject = new JSONObject(response);
        JSONObject hits = jsonObject.getJSONObject("hits");
        JSONArray arrayHits = hits.getJSONArray("hits");
        for(int i =0 ; i< arrayHits.length(); i++){
            System.out.print(arrayHits.getJSONObject(i).getJSONObject("_source"));
            System.out.print("\n");
        }
    }

    public List<String> getLastLogs(int count, String indexName, int run_id) throws IOException {
        URL url = new URL("http://localhost:9200/" + indexName + "/_search");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        OutputStream os = con.getOutputStream();

        String json = buildJson(run_id, count);

        os.write(json.getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();
        con.connect();
        int code = con.getResponseCode();
        if (code < 200 || code >= 300){
            con.disconnect();
            throw new RuntimeException("ElasticSearch returned code: " + code);
        }
//		FOR DEBUG:
        byte[] bajty = new byte[1000];
        con.getInputStream().read(bajty);
        printResponse(new String(bajty));
        con.disconnect();
        return null;
    }
    public List<String> getLogs(Instant timeFrom, Instant timeTo){
        return null;
    }
    public List<String> getLogs(String moduleName){
        return null;
    }
}
