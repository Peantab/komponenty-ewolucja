package pl.edu.agh.iisg.ewolucja.operators.continuos;

import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.logger.Logger;

import java.io.IOException;
import java.util.Arrays;

public class NormalizedSumEvaluation implements Operator {
	 private Logger logger;

    @Override
    public void initialize(Configuration configuration) {
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
		 
        double sum = population.getIndividuals().stream()
                .mapToDouble(this::updateFitness)
                .sum();
        population.getIndividuals().forEach(ind -> ind.setFitness(ind.getFitness() / sum));

        try {
            logger.log(population.toString());
            logger.endWork();
        } catch (IOException e) {
            System.out.println("Exception during logging process");
            e.printStackTrace();
        }
		
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
