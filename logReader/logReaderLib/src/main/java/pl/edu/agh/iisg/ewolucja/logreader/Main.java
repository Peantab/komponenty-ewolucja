package pl.edu.agh.iisg.ewolucja.logreader;

import java.io.IOException;


public class Main {
	public static void main(String[] args) throws IOException {
		LogReader logReader = new LogReader();
		logReader.getLogs(99989,"general2");
	}
}
