package pl.edu.agh.iisg.ewolucja.operators.continuos;

import pl.edu.agh.iisg.ewolucja.core.*;

import java.util.Arrays;

public class NormalizedSumEvaluation implements Operator {

    @Override
    public void initialize(Configuration configuration) {

    }

    @Override
    public Population apply(Population population) {
        double sum = population.getIndividuals().stream()
                .mapToDouble(this::updateFitness)
                .sum();
        population.getIndividuals().forEach(ind -> ind.setFitness(ind.getFitness() / sum));
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
