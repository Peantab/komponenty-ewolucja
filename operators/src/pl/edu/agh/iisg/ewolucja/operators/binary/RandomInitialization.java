package pl.edu.agh.iisg.ewolucja.operators.binary;

import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.operators.exceptions.MissingParameterException;

import java.util.Random;

public class RandomInitialization implements Operator {

    private int populationSize;
    private int genotypeSize;
    private ProblemType type;
    private Random random =  new Random();

    private RandomInitialization(int populationSize, int genotypeSize, ProblemType type) {
        this.populationSize = populationSize;
        this.genotypeSize = genotypeSize;
        this.type = type;
    }

    @Override
    public void initialize(Configuration configuration) {
        Integer popSize = (Integer) configuration.getParameter("population.size");
        Integer genSize = (Integer) configuration.getParameter("genotype.size");
        ProblemType type = (ProblemType) configuration.getParameter("problem.type");
        if (popSize == null) {
            throw new MissingParameterException("population.size");
        }
        if (genSize == null) {
            throw new MissingParameterException("genotype.size");
        }
        if (type == null) {
            throw new MissingParameterException("problem.type");
        }
        this.populationSize = popSize;
        this.genotypeSize = genSize;
        this.type = type;
    }

    @Override
    public Population apply(Population population) {
        for (int i = 0;i < populationSize - population.getIndividuals().size();i++) {
            population.getIndividuals().add(new Individual(getRandomGenotype()));
        }
        return population;
    }

    private Gene[] getRandomGenotype() {
        Gene[] genotype = new Gene[genotypeSize];
        for (int i=0;i<genotypeSize;i++){
            double value = random.nextDouble() > 0.5 ? 1.0 : 0.0;
            genotype[i] = new Gene(value);
        }
        return genotype;
    }

    @Override
    public ProblemType getType() {
        return type;
    }
}
