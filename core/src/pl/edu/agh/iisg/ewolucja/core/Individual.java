package pl.edu.agh.iisg.ewolucja.core;

public class Individual {
	
	private Gene[] genotype; 
	private double fitness;

	public Gene[] getGenotype() {
		return genotype;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public Individual(Gene[] genotype) {
		this.genotype = genotype;
		this.fitness = 0;
	}

}
