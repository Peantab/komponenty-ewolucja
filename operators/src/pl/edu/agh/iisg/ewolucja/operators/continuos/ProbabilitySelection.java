package pl.edu.agh.iisg.ewolucja.operators.continuos;

import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.logger.Logger;
import pl.edu.agh.iisg.ewolucja.operators.exceptions.MissingParameterException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProbabilitySelection implements Operator {

    private Integer individualsSelected;
	 private Logger logger = new Logger();

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
		 logger.startWork();
		 try {
			logger.log(population.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
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
		
		try {
			logger.log(population.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.endWork();
        return population;
    }

    @Override
    public ProblemType getType() {
        return ProblemType.CONTINUOUS;
    }
}
