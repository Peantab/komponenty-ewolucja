package pl.edu.agh.iisg.ewolucja.operators.binary;

import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.operators.exceptions.MissingParameterException;

import java.util.Arrays;

public class SumEvaluation implements Operator {

    private ProblemType type;

    @Override
    public void initialize(Configuration configuration) {
        ProblemType type = (ProblemType) configuration.getParameter("problem.type");
        if (type == null) {
            throw new MissingParameterException("problem.type");
        }
        this.type = type;
    }

    @Override
    public Population apply(Population population) {
        population.getIndividuals().forEach(this::updateFitness);
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
