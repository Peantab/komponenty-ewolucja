package pl.edu.agh.iisg.ewolucja.operators.continuos;

import java.util.List;
import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.operators.exceptions.MissingParameterException;
import java.util.Random;

public class PermutationMutation implements Operator {
	 private ProblemType type = ProblemType.CONTINUOUS;
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
		 Random rgen = new Random();			
		 
		 for (int i=0; i < genotype.length; i++) {
			 int randomPosition = rgen.nextInt(genotype.length);
			 Gene temp = genotype[i];
			 genotype[i] = genotype[randomPosition];
			 genotype[randomPosition] = temp;
		 }
	 }

	 @Override
	 public ProblemType getType() {
	    return type;
	 }
}