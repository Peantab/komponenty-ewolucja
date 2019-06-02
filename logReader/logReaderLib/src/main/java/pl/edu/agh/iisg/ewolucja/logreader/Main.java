package pl.edu.agh.iisg.ewolucja.logreader;

import java.io.IOException;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
	    String usage = "[LOG READER APP]\n" +
                "USAGE:\n" +
                "Enter one of the numbers:\n" +
                "1. to get logs from a particular run from a particular index;\n" +
                "2. to get at most a certain amount of entries from a particular run from a particular index;\n" +
                "3. to get logs from a particular index from particular timespan.\n" +
                "0. to quit";
        System.out.print(usage);
        LogReader logReader = new LogReader();
        Scanner scanner = new Scanner(System.in);
	    while(true){
	        System.out.print("Enter an option [0-3]: ");
            String functionName = scanner.next();
            if(functionName.equals("1")){
                System.out.print("Enter run_id: ");
                String run_id = scanner.next();
                long id = Long.parseLong(run_id);
                System.out.print("Enter index_name: ");
                String index_name = scanner.next();
                logReader.getLogs(id, index_name);

            }else if(functionName.equals("2")){
                System.out.print("Enter run_id: ");
                String run_id = scanner.next();
                long id = Long.parseLong(run_id);
                System.out.print("Enter index_name: ");
                String index_name = scanner.next();
                System.out.print("Enter number_of_logs: ");
                int count = Integer.parseInt(scanner.next());
                logReader.getLastLogs(count, index_name, id);
            }else if(functionName.equals("3")){
                System.out.print("Enter index_name: ");
                String index_name = scanner.next();
                System.out.print("Enter timestamp from: ");
                String timeFrom = scanner.next();
                System.out.print("Enter timestamp to: ");
                String timeTo = scanner.next();
                logReader.getLogs(index_name, timeFrom, timeTo);
            }else if(functionName.equals("0")){
                break;
            }else{
                System.out.print(usage);
            }
        }
	}
}
