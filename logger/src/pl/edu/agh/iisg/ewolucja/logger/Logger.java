package pl.edu.agh.iisg.ewolucja.logger;

public class Logger {
	public Logger(){
		System.out.println("Started mock logger.");
	}
	
	public void startWork() {
		System.out.println("Mock logger started work.");
	}

	public void endWork() {
		System.out.println("Mock logger finished work.");
	}

	public void log(String json) {
		System.out.println("Mock-logged: " + json);
	}

	public void logStructured(int a, int b, int c) {
		System.out.println("Mock-logged: " + a + b + c);
	}
}
