package pl.edu.agh.iisg.ewolucja.logger;

import pl.edu.agh.iisg.ewolucja.logger.schemes.AbstractLog;
import pl.edu.agh.iisg.ewolucja.logger.schemes.GeneralLog;
import pl.edu.agh.iisg.ewolucja.logger.schemes.LifecycleLog;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

public class Logger {
	private final long runId;
	private int sequenceNumber = 0;
	private String caller;

	public Logger() throws IOException{
		runId = Instant.now().toEpochMilli();
		caller = new Exception().getStackTrace()[1].getClassName();
		LifecycleLog lifecycleLog = new LifecycleLog(runId, caller, LifecycleLog.LifecycleState.START_WORK);
		sendLog(lifecycleLog);
		System.out.println("Started mock logger.");
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

	public void logStructured(int a, int b, int c) {
		System.out.println("Mock-logged: " + a + b + c);
	}

	private void sendLog(AbstractLog log) throws IOException {
		URL url = new URL("http://localhost:9200/" + log.getIndexName() + '/' + log.getIndexName() + '/' + runId + sequenceNumber++);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("PUT");
		con.setRequestProperty("Content-Type", "application/json");
		OutputStream os = con.getOutputStream();
		System.out.println(log.getJson());
		os.write(log.getJson().getBytes(StandardCharsets.UTF_8));
		os.flush();
		os.close();
		con.connect();
//		FOR DEBUG:
//		System.out.println(con.getResponseCode());
//		byte[] bajty = new byte[1000];
//		con.getInputStream().read(bajty);
//		System.out.println(new String(bajty));
		con.disconnect();
	}
}
