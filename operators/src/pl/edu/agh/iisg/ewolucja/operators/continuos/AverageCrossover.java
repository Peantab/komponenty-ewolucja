package pl.edu.agh.iisg.ewolucja.operators.continuos;

import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AverageCrossover implements Operator {

    private ProblemType type = ProblemType.CONTINUOUS;
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
		 
        List<Individual> crossed = new ArrayList<>();
        List<Individual> individuals = population.getIndividuals();
        for (int i=0;i<individuals.size() - 1 && crossed.size() < individuals.size();i++) {
            for (int j=i+1;j<individuals.size() && crossed.size() < individuals.size();j++) {
                crossed.add(cross(individuals.get(i), individuals.get(j)));
            }
        }
        population.setIndividuals(crossed);

        try {
            logger.log(population.toString());
            logger.endWork();
        } catch (IOException e) {
            System.out.println("Exception during logging process");
            e.printStackTrace();
        }
		
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
