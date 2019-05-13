package pl.edu.agh.iisg.ewolucja.operators.continuos;

import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.operators.exceptions.MissingParameterException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProbabilitySelection implements Operator {

    private Integer individualsSelected;

    @Override
    public void initialize(Configuration configuration) {
        Integer indSel = (Integer) configuration.getParameter("individuals.selected");
        if (indSel == null) {
            throw new MissingParameterException("individuals.selected");
        }
        this.individualsSelected = indSel;
    }

    @Override
    public Population apply(Population population) {
        List<Individual> individuals = population.getIndividuals();
        List<Individual> selected = new ArrayList<>();
        Random rand = new Random();
        double sum = individuals.stream()
                .mapToDouble(Individual::getFitness)
                .sum();
        while (selected.size() < individualsSelected) {
            int index = rand.nextInt(individuals.size());
            Individual toSelect = individuals.get(index);
            if (rand.nextDouble() > toSelect.getFitness() / sum) {
                selected.add(individuals.remove(index));
            }
        }
        population.setIndividuals(selected);
        return population;
    }

    @Override
    public ProblemType getType() {
        return ProblemType.CONTINUOUS;
    }
}
