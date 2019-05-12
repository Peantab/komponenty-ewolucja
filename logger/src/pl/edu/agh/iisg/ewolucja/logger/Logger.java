package pl.edu.agh.iisg.ewolucja.logger;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class Logger {
	private int idCounter = 0;
	private final RestHighLevelClient client;
	public Logger(){
		client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
		System.out.println("Started mock logger.");
	}
	
	public void startWork() {
		System.out.println("Mock logger started work.");
	}

	public void endWork() {
		System.out.println("Mock logger finished work.");
	}

	public void log(String json) throws IOException {
		IndexRequest request = new IndexRequest("temporaryIndexName");
		request.id(String.valueOf(++idCounter));
		request.source(json, XContentType.JSON);
		client.index(request, RequestOptions.DEFAULT);
	}

	public void logStructured(int a, int b, int c) {
		System.out.println("Mock-logged: " + a + b + c);
	}
}
