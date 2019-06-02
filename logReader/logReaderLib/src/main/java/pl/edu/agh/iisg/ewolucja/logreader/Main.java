package pl.edu.agh.iisg.ewolucja.logreader;

import java.io.IOException;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
	    String usage = "[LOG READER APP]\n" +
                "[USAGE function args]\n" +
                "getLogs run_id index_name \n" +
                "getLastLogs number_of_logs index_name run_id \n" +
                "getLogsFromTo index_name timeFrom timeTo \n";
        System.out.print(usage);
        LogReader logReader = new LogReader();
        Scanner scanner = new Scanner(System.in);
	    while(true){
	        System.out.print("Enter function name: ");
            String functionName = scanner.next();
            if(functionName.equals("getLogs")){
                System.out.print("Enter run_id: ");
                String run_id = scanner.next();
                int id = Integer.parseInt(run_id);
                System.out.print("Enter index_name: ");
                String index_name = scanner.next();
                logReader.getLogs(id, index_name);

            }else if(functionName.equals("getLastLogs")){
                System.out.print("Enter run_id: ");
                String run_id = scanner.next();
                int id = Integer.parseInt(run_id);
                System.out.print("Enter index_name: ");
                String index_name = scanner.next();
                System.out.print("Enter number_of_logs: ");
                int count = Integer.parseInt(scanner.next());
                logReader.getLastLogs(count, index_name, id);
            }else if(functionName.equals("getLogsFromTo")){
                System.out.print("Enter index_name: ");
                String index_name = scanner.next();
                System.out.print("Enter timestamp from: ");
                String timeFrom = scanner.next();
                System.out.print("Enter timestamp to: ");
                String timeTo = scanner.next();
                logReader.getLogs(index_name, timeFrom, timeTo);
            }else{
                System.out.print(usage);
            }
        }
	}
}
