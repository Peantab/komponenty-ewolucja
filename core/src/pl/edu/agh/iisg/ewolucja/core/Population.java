package pl.edu.agh.iisg.ewolucja.core;

import java.util.List;

public abstract class Population {
	
	private int generation;
	private List<Individual> individuals;
	private ProblemType type;

	public List<Individual> getIndividuals() {
		return individuals;
	}

	public void setIndividuals(List<Individual> individuals) {
		this.individuals = individuals;
	}
}
