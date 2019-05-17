package pl.edu.agh.iisg.ewolucja.operators.binary;

import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.logger.Logger;
import pl.edu.agh.iisg.ewolucja.operators.exceptions.MissingParameterException;

import java.io.IOException;
import java.util.*;

public class HighestFitnessSelection implements Operator {

    private int individualsSelected;
    private ProblemType type = ProblemType.BINARY;
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
        individuals.sort(Comparator.comparingDouble(Individual::getFitness).reversed());
        List<Individual> selected = new ArrayList<>();
        for (int i=0;i<individualsSelected;i++) {
            selected.add(individuals.get(i));
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
        return type;
    }
}
