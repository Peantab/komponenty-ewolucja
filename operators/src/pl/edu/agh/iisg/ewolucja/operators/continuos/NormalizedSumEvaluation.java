package pl.edu.agh.iisg.ewolucja.operators.continuos;

import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.logger.Logger;

import java.io.IOException;
import java.util.Arrays;

public class NormalizedSumEvaluation implements Operator {
	 private Logger logger = new Logger();

    @Override
    public void initialize(Configuration configuration) {

    }

    @Override
    public Population apply(Population population) {
		 logger.startWork();
		 try {
			logger.log(population.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
        double sum = population.getIndividuals().stream()
                .mapToDouble(this::updateFitness)
                .sum();
        population.getIndividuals().forEach(ind -> ind.setFitness(ind.getFitness() / sum));
		
		try {
			logger.log(population.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.endWork();
		
        return population;
    }

    private double updateFitness(Individual ind) {
        double fitness = Arrays.stream(ind.getGenotype())
                .mapToDouble(Gene::getValue)
                .sum();
        ind.setFitness(fitness);
        return fitness;
    }

    @Override
    public ProblemType getType() {
        return ProblemType.CONTINUOUS;
    }
}
