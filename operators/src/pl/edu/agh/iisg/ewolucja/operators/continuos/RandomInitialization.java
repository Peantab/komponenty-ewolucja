package pl.edu.agh.iisg.ewolucja.operators.continuos;

import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.logger.Logger;
import pl.edu.agh.iisg.ewolucja.operators.exceptions.MissingParameterException;

import java.io.IOException;
import java.util.Random;

public class RandomInitialization implements Operator {

	private int populationSize;
	private int genotypeSize;
	private ProblemType type = ProblemType.CONTINUOUS;
	private Random random = new Random();
	 private Logger logger;

	private RandomInitialization(int populationSize, int genotypeSize) {
		this.populationSize = populationSize;
		this.genotypeSize = genotypeSize;
	}

	@Override
	public void initialize(Configuration configuration) {
		Integer popSize = (Integer) configuration.getParameter("population.size");
		Integer genSize = (Integer) configuration.getParameter("genotype.size");
		if (popSize == null) {
			throw new MissingParameterException("population.size");
		}
		if (genSize == null) {
			throw new MissingParameterException("genotype.size");
		}
		this.populationSize = popSize;
		this.genotypeSize = genSize;
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
		 
		for (int i = 0; i < populationSize - population.getIndividuals().size(); i++) {
			population.getIndividuals().add(new Individual(getRandomGenotype()));
		}

		try {
			logger.log(population.toString());
			logger.endWork();
		} catch (IOException e) {
			System.out.println("Exception during logging process");
			e.printStackTrace();
		}

		return population;
	}

	private Gene[] getRandomGenotype() {
		Gene[] genotype = new Gene[genotypeSize];
		for (int i = 0; i < genotypeSize; i++) {
			double value = random.nextDouble();
			genotype[i] = new Gene(value);
		}
		return genotype;
	}

	@Override
	public ProblemType getType() {
		return type;
	}
}
