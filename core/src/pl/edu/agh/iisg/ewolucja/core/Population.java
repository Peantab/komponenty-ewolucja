package pl.edu.agh.iisg.ewolucja.core;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Population {
	
	private int generation;
	private List<Individual> individuals;

	public Population() {
		this.generation = 0;
		this.individuals = new ArrayList<>();
	}

	public List<Individual> getIndividuals() {
		return individuals;
	}

	public void setIndividuals(List<Individual> individuals) {
		this.individuals = individuals;
	}
	
	public int getGeneration() {
		return this.generation;
	}
	
	public void setGeneration(int generation) {
		this.generation = generation;
	}
	
	public String toString() {
		JSONObject populationJsonObject = new JSONObject();
		JSONArray individualsJsonArray = new JSONArray();

		for (int i = 0; i < individuals.size(); i++) {
			Individual individual = individuals.get(i);
			Gene[] genotype = individual.getGenotype();
			JSONObject individualJsonObject = new JSONObject();
			JSONArray genotypeJsonArray = new JSONArray();

			for (int j = 0; j < genotype.length; j++) {
				genotypeJsonArray.put(genotype[j].getValue());
			}

			individualJsonObject.put("fitness", individual.getFitness());
			individualJsonObject.put("genotype", genotypeJsonArray);

			individualsJsonArray.put(individualJsonObject);
		}

		populationJsonObject.put("generation", generation);
		populationJsonObject.put("individuals", individualsJsonArray);

		return populationJsonObject.toString();
	}
}
