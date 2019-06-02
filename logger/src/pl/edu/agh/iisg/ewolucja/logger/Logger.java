package pl.edu.agh.iisg.ewolucja.logger;

import pl.edu.agh.iisg.ewolucja.logger.schemes.AbstractLog;
import pl.edu.agh.iisg.ewolucja.logger.schemes.GeneralLog;
import pl.edu.agh.iisg.ewolucja.logger.schemes.LifecycleLog;
import pl.edu.agh.iisg.ewolucja.logger.schemes.MapBasedLog;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Map;

public class Logger {
	private final long runId;
	private String caller;

	public Logger() throws IOException{
		runId = Instant.now().toEpochMilli();
		caller = new Exception().getStackTrace()[1].getClassName();
		LifecycleLog lifecycleLog = new LifecycleLog(runId, caller, LifecycleLog.LifecycleState.START_WORK);
		sendLog(lifecycleLog);
	}
	
	public void startWork() throws IOException {
		caller = new Exception().getStackTrace()[1].getClassName();
		LifecycleLog lifecycleLog = new LifecycleLog(runId, caller, LifecycleLog.LifecycleState.TAKE_OVER_WORK);
		sendLog(lifecycleLog);
	}

	public void endWork() throws IOException{
		LifecycleLog lifecycleLog = new LifecycleLog(runId, caller, LifecycleLog.LifecycleState.END_WORK);
		sendLog(lifecycleLog);
	}

	public void log(String json) throws IOException {
		GeneralLog generalLog = new GeneralLog(runId, caller, json);
		sendLog(generalLog);
	}

	public void log(Map<String, String> entries) throws IOException {
		MapBasedLog mapBasedLog = new MapBasedLog(runId, caller, entries);
		sendLog(mapBasedLog);
	}

	private void sendLog(AbstractLog log) throws IOException {
		URL url = new URL("http://localhost:9200/" + log.getIndexName() + "/_doc");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		OutputStream os = con.getOutputStream();
		os.write(log.getJson().getBytes(StandardCharsets.UTF_8));
		os.flush();
		os.close();
		con.connect();
		int code = con.getResponseCode();
		if (code < 200 || code >= 300){
			con.disconnect();
			throw new RuntimeException("ElasticSearch returned code: " + code);
		}
//		FOR DEBUG:
//		System.out.println(con.getResponseCode());
//		byte[] bajty = new byte[1000];
//		con.getInputStream().read(bajty);
//		System.out.println(new String(bajty));
		con.disconnect();
		printLog(log);
	}

	private void printLog(AbstractLog log){
		Instant timestamp = Instant.ofEpochMilli(log.getTimestamp());
		String headers = "[" + timestamp.toString() + "][" + log.getIndexName() + "]";
		System.out.print(headers + " " + log.getJson().replace("\n", " ") + "\n");
	}
}
