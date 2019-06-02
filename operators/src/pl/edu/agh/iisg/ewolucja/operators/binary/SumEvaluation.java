package pl.edu.agh.iisg.ewolucja.operators.binary;

import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.logger.Logger;

import java.io.IOException;
import java.util.Arrays;

public class SumEvaluation implements Operator {

    private ProblemType type = ProblemType.BINARY;
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
		 
        population.getIndividuals().forEach(this::updateFitness);

        try {
            logger.log(population.toString());
            logger.endWork();
        } catch (IOException e) {
            System.out.println("Exception during logging process");
            e.printStackTrace();
        }
        return population;
    }

    private void updateFitness(Individual ind) {
        ind.setFitness(
            Arrays.stream(ind.getGenotype())
                .mapToDouble(Gene::getValue)
                .sum()
        );
    }

    @Override
    public ProblemType getType() {
        return type;
    }
}
