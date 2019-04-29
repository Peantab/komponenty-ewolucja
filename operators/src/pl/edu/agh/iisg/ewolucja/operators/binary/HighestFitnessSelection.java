package pl.edu.agh.iisg.ewolucja.operators.binary;

import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.operators.exceptions.MissingParameterException;

import java.util.*;

public class HighestFitnessSelection implements Operator {

    private int individualsSelected;
    private ProblemType type;

    @Override
    public void initialize(Configuration configuration) {
        Integer indSel = (Integer) configuration.getParameter("individuals.selected");
        ProblemType type = (ProblemType) configuration.getParameter("problem.type");
        if (indSel == null) {
            throw new MissingParameterException("individuals.selected");
        }
        if (type == null) {
            throw new MissingParameterException("problem.type");
        }
        this.individualsSelected = indSel;
        this.type = type;
    }

    @Override
    public Population apply(Population population) {
        List<Individual> individuals = population.getIndividuals();
        individuals.sort(Comparator.comparingDouble(Individual::getFitness).reversed());
        List<Individual> selected = new ArrayList<>();
        for (int i=0;i<individualsSelected;i++) {
            selected.add(individuals.get(i));
        }
        population.setIndividuals(selected);
        return population;
    }

    @Override
    public ProblemType getType() {
        return type;
    }
}
