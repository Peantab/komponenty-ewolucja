package pl.edu.agh.iisg.ewolucja.operators.binary;

import java.util.List;

import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.operators.exceptions.MissingParameterException;

public class SwapMutation implements Operator {
	 private ProblemType type = ProblemType.BINARY;
	 private double mutationProbability;

	 @Override
	 public void initialize(Configuration configuration) {
		 Double mutationProbability = (Double) configuration.getParameter("problem.mutationProbability");
	     if (mutationProbability == null) {
	        throw new MissingParameterException("problem.mutationProbability");
	     }
	     this.mutationProbability = mutationProbability;
	 }

	 @Override
	 public Population apply(Population population) {
		List<Individual> individuals = population.getIndividuals();
		int n = individuals.size();
		
		for (int i = 0; i < n; i++) {
			if (Math.random() < mutationProbability){
				this.mutate(individuals.get(i).getGenotype());
			}
		}

	    return population;
	 }
	 
	 private void mutate(Gene[] genotype){
		 int geneA = (int)(Math.random() * (genotype.length-1));
		 int geneB = (int)(Math.random() * (genotype.length-1));
		 
		 if (geneA != geneB) {
			 Gene tmp = genotype[geneA];
			 genotype[geneA] = genotype[geneB];
			 genotype[geneB] = tmp;
		 }
	 }

	 @Override
	 public ProblemType getType() {
	    return type;
	 }
}