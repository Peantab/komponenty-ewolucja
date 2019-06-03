package pl.edu.agh.iisg.ewolucja.main;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.edu.agh.iisg.ewolucja.core.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

	private JSONArray jsonArray;
	private String problemType;
	private int iterations;
	private Operator operator;
	private Configuration configuration;
	private Population population;
	private int genotypeSize;

	public Main(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
		for (int i = 0 ; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			if(obj.getString("name").equals("GLOBALINFO")){
				this.problemType = obj.getString("problemType");
				this.iterations = obj.getInt("iterations");
			} else if(obj.getString("name").equals("CONFIGURATION")){
				this.genotypeSize = obj.getInt("genotype.size");
				configInit(obj);
			} else if(obj.getString("name").equals("POPULATION")){
				populationInit(obj);
			} else {
				step(problemType, iterations, obj);
			}
		}
	}

	private void configInit(JSONObject obj){
		Map<String,Object> configMap = new HashMap<String, Object>();

		configMap.put("individuals.selected", obj.getInt("individuals.selected"));
		configMap.put("problem.crossoverProbability", obj.getDouble("problem.crossoverProbability"));
		configMap.put("population.size", obj.getInt("population.size"));
		configMap.put("genotype.size", obj.getInt("genotype.size"));
		configMap.put("problem.mutationProbability", obj.getDouble("problem.mutationProbability"));

		this.configuration = new Configuration(configMap);
	}

	private void populationInit(JSONObject obj){
		if (this.problemType.equals("BINARY")){
			// Creating ArrayList of Genes
			ArrayList<Gene> genes = new ArrayList<>();
			JSONArray jgenes = obj.getJSONArray("allGenes");
			for (int i = 0; i < jgenes.length(); i++){
				Gene gene = new Gene(jgenes.getDouble(i));
				genes.add(gene);
			}

			// Creating ArrayList of Individuals
			ArrayList<Individual> individuals = new ArrayList<>();
			Iterator geneIterator = genes.iterator();
			while (geneIterator.hasNext()){
				Gene[] geneArray = new Gene[this.genotypeSize];
				for (int i = 0; i < this.genotypeSize; i++) {
					geneArray[i] = (Gene) geneIterator.next();
				}
				Individual individual = new Individual(geneArray);
				individuals.add(individual);
			}

			// Creating Population
			this.population = new Population();
			this.population.setIndividuals(individuals);
			this.population.setGeneration(1);
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
