package pl.edu.agh.iisg.ewolucja.main;

public class Main {

	private JSONArray jsonArray;
	private String problemType;
	private int iterations;
	private Operator operator;

	public Main(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
		for (int i = 0 ; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			if(obj.getString("name").equals("GLOBALINFO")){
				this.problemType = obj.getString("problemType");
				this.iterations = obj.getInt("iterations")
			} else {
				step(problemType, iterations, obj)
			}
		}
	}

	private void step(String problemType, int iterations, JSONObject obj){
		String name = obj.getString("name");
		switch (problemType){
			case "BINARY":
				runBinaryOperator(name);
				break;
			case "CONTINUOUS":
				runContinousOperator(name);
				break;
			case "COMBINATORIAL":
				runCombinatorialOperator(name);
				break;
		}

	}

	private void runBinaryOperator(String name) {
		switch (name){
			case "INITIATION":
				break;
			case "EVALUATION":
				break;
			case "SELECTION":
				break;
			case "CROSSING":
				break;
			case "MUTATION":
				break;
		}
	}

	private void runContinousOperator(String name) {
		switch (name){
			case "INITIATION":
				break;
			case "EVALUATION":
				break;
			case "SELECTION":
				break;
			case "CROSSING":
				break;
			case "MUTATION":
				break;
		}
	}

	private void runCombinatorialOperator(String name) {
		switch (name){
			case "INITIATION":
				break;
			case "EVALUATION":
				break;
			case "SELECTION":
				break;
			case "CROSSING":
				break;
			case "MUTATION":
				break;
		}
	}
}
