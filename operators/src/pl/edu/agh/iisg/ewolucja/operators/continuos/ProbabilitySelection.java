package pl.edu.agh.iisg.ewolucja.operators.continuos;

import pl.edu.agh.iisg.ewolucja.core.*;

import java.util.ArrayList;
import java.util.List;

public class RouletteWheelSelection implements Operator {

    @Override
    public void initialize(Configuration configuration) {

    }

    @Override
    public Population apply(Population population) {
        List<Individual> individuals = population.getIndividuals();
        List<Individual> selected = new ArrayList<>();
        double sum = individuals.stream()
                .mapToDouble(Individual::getFitness)
                .sum();
        Double[] probabilityThresholds = new Double[individuals.size()];
        probabilityThresholds[0] = individuals.get(0).getFitness() / sum;
        for (int i=1;i<individuals.size();i++) {
            probabilityThresholds[i] = probabilityThresholds[i-1] + individuals.get(i).getFitness() / sum;
        }

        return null;
    }

    @Override
    public ProblemType getType() {
        return ProblemType.CONTINUOUS;
    }
}
