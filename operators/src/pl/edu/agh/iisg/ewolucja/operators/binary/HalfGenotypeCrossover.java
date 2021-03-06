package pl.edu.agh.iisg.ewolucja.operators.binary;

import java.io.IOException;
import java.util.List;
import pl.edu.agh.iisg.ewolucja.logger.*;

import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.operators.exceptions.MissingParameterException;

public class HalfGenotypeCrossover implements Operator {
	 private ProblemType type = ProblemType.BINARY;
	 private double crossoverProbability;
	 private Logger logger;

	 @Override
	 public void initialize(Configuration configuration) {
		 Double crossoverProbability = (Double) configuration.getParameter("problem.crossoverProbability");
	     if (crossoverProbability == null) {
	        throw new MissingParameterException("problem.crossoverProbability");
	     }
	     this.crossoverProbability = crossoverProbability;
		 try {
			 this.logger = new Logger();
		 } catch (IOException e) {
			 e.printStackTrace();
			 System.out.println("Exception occurred while initializing logger");
		 }
	 }

	 @Override
	 public Population apply(Population population) {
	 	try {
		 	logger.startWork();
			logger.log(population.toString());
		} catch (IOException e) {
			System.out.println("Exception during logging process");
			e.printStackTrace();
		}
		 
		List<Individual> individuals = population.getIndividuals();
		int n = individuals.size();
		
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (Math.random() < crossoverProbability){
					individuals.add(this.cross(individuals.get(i), individuals.get(j)));
				}
			}
		}
		
		try {
			logger.log(population.toString());
			logger.endWork();
		} catch (IOException e) {
			System.out.println("Exception during logging process");
			e.printStackTrace();
		}

	    return population;
	 }
	 
	 private Individual cross(Individual indA, Individual indB){
		 Gene[] a = indA.getGenotype();
		 Gene[] b = indB.getGenotype();
		 int middleIndex = (int) Math.floor(a.length/2);
		 Gene[] genotype = new Gene[a.length];
		 
		    for (int i = 0; i <a.length; i++) {
		        if (i < middleIndex){
		        	genotype[i] = a[i];
		        }
		        else {
		        	genotype[i] = b[i];
		        }
		    }
				 
		return new Individual(genotype);
	}

	 @Override
	 public ProblemType getType() {
	    return type;
	 }
}