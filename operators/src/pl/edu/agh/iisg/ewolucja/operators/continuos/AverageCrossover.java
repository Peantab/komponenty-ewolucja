package pl.edu.agh.iisg.ewolucja.operators.continuos;

import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.operators.exceptions.MissingParameterException;

import java.util.ArrayList;
import java.util.List;

public class AverageCrossover implements Operator {

    private ProblemType type = ProblemType.CONTINUOUS;

    @Override
    public void initialize(Configuration configuration) {

    }

    @Override
    public Population apply(Population population) {
        List<Individual> crossed = new ArrayList<>();
        List<Individual> individuals = population.getIndividuals();
        for (int i=0;i<individuals.size() - 1 && crossed.size() < individuals.size();i++) {
            for (int j=i+1;j<individuals.size() && crossed.size() < individuals.size();j++) {
                crossed.add(cross(individuals.get(i), individuals.get(j)));
            }
        }
        population.setIndividuals(crossed);
        return population;
    }

    private Individual cross(Individual mom, Individual dad) {
        Gene[] childGenes = new Gene[mom.getGenotype().length];
        Gene[] momsGenes = mom.getGenotype();
        Gene[] dadsGenes = dad.getGenotype();
        for (int i=0;i<childGenes.length;i++) {
            childGenes[i] = new Gene((momsGenes[i].getValue() + dadsGenes[i].getValue())/2.0);
        }
        return new Individual(childGenes);
    }


    @Override
    public ProblemType getType() {
        return ProblemType.CONTINUOUS;
    }
}
