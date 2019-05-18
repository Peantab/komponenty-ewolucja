package pl.edu.agh.iisg.ewolucja.logger;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import pl.edu.agh.iisg.ewolucja.logger.schemes.AbstractLog;
import pl.edu.agh.iisg.ewolucja.logger.schemes.GeneralLog;
import pl.edu.agh.iisg.ewolucja.logger.schemes.LifecycleLog;

import java.io.IOException;
import java.time.Instant;

public class Logger {
	private final long runId;
	private String caller;
	private final RestHighLevelClient client;

	public Logger() throws IOException{
		runId = Instant.now().toEpochMilli();
		caller = new Exception().getStackTrace()[1].getClassName();
		client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
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

	private void sendLog(AbstractLog log) throws IOException{
		IndexRequest request = new IndexRequest(log.getIndexName());
		request.source(log.getMap(), XContentType.JSON);
		client.index(request, RequestOptions.DEFAULT);
	}
}
