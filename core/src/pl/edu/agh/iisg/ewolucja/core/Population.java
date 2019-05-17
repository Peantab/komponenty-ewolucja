package pl.edu.agh.iisg.ewolucja.core;

import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Population {
	
	private int generation;
	private List<Individual> individuals;

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
		JsonObject populationJsonObject = new JsonObject();
		JsonArray individualsJsonArray = new JsonArray();

		for (int i = 0; i < individuals.size(); i++) {
			Individual individual = individuals.get(i);
			Gene[] genotype = individual.getGenotype();
			JsonObject individualJsonObject = new JsonObject();
			JsonArray genotypeJsonArray = new JsonArray();

			for (int j = 0; j < genotype.length; j++) {
				genotypeJsonArray.add(genotype[j].getValue());
			}

			individualJsonObject.addProperty("fitness", individual.getFitness());
			individualJsonObject.add("genotype", genotypeJsonArray);

			individualsJsonArray.add(individualJsonObject);
		}

		populationJsonObject.addProperty("generation", generation);
		populationJsonObject.add("individuals", individualsJsonArray);

		return populationJsonObject.toString();
	}
}
