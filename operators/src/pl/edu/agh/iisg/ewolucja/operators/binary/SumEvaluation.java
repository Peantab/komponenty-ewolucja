package pl.edu.agh.iisg.ewolucja.operators.binary;

import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.logger.Logger;

import java.io.IOException;
import java.util.Arrays;

public class SumEvaluation implements Operator {

    private ProblemType type = ProblemType.BINARY;
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
		 
        population.getIndividuals().forEach(this::updateFitness);

		try {
			logger.log(population.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.endWork();
		
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
