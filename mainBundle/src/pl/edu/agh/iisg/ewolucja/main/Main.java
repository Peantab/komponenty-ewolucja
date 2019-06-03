package pl.edu.agh.iisg.ewolucja.main;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import pl.edu.agh.iisg.ewolucja.core.*;
import pl.edu.agh.iisg.ewolucja.operators.binary.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main implements BundleActivator {

    private String problemType;
    private int iterations;
    private Operator operator;
    private Configuration configuration;
    private Population population;
    private int genotypeSize;
    private String[] steps = {"SumEvaluation", "HalfGenotypeCrossover", "HighestFitnessSelection", "RandomInitialization", "SwapMutation"};

    public Main() {
    }

    private void configInit(){
        Map<String,Object> configMap = new HashMap<String, Object>();

        configMap.put("individuals.selected", 4);
        configMap.put("problem.crossoverProbability", 0.4);
        configMap.put("population.size", 8);
        configMap.put("genotype.size", 2);
        configMap.put("problem.mutationProbability", 0.1);

        this.configuration = new Configuration(configMap);
    }

    private void populationInit(){
        if (this.problemType.equals("BINARY")){
            // Creating ArrayList of Genes
            ArrayList<Gene> genes = new ArrayList<>();
            Double[] jgenes = {1.0,3.0,42.0,52.0,12.0,42.0,42.0,2.0,47.0,98.0,24.0,21.0,0.0,8.0,28.0,77.0};
            for (Double jgene : jgenes) {
                Gene gene = new Gene(jgene);
                genes.add(gene);
            }

            // Creating ArrayList of Individuals
            ArrayList<Individual> individuals = new ArrayList<>();
            Iterator geneIterator = genes.iterator();
            while (geneIterator.hasNext()){
                Gene[] geneArray = new Gene[this.genotypeSize];
                for (int i = 0; i < this.genotypeSize; i++) {
                    geneArray[i] = (Gene) geneIterator.next();
                }
                Individual individual = new Individual(geneArray);
                individuals.add(individual);
            }

            // Creating Population
            this.population = new Population();
            this.population.setIndividuals(individuals);
            this.population.setGeneration(1);
        }
    }

    private void step(String problemType, int iterations){
        switch (problemType){
            case "BINARY":
                for(String step : steps)
                    runBinaryOperator(step);
                break;
            case "CONTINUOUS":
                for(String step : steps)
                    runContinousOperator(step);
                break;
            case "COMBINATORIAL":
                for(String step : steps)
                    runCombinatorialOperator(step);
                break;
        }

    }

    private void runBinaryOperator(String name) {
        switch (name){
            case "SumEvaluation":
                Operator sumEvaluation = new SumEvaluation();
                sumEvaluation.initialize(configuration);
                sumEvaluation.apply(population);
                break;
            case "HalfGenotypeCrossover":
                try {
                    HalfGenotypeCrossover halfGenotypeCrossover = new HalfGenotypeCrossover();
                    halfGenotypeCrossover.initialize(configuration);
                    halfGenotypeCrossover.apply(population);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "HighestFitnessSelection":
                try {
                    HighestFitnessSelection highestFitnessSelection = new HighestFitnessSelection();
                    highestFitnessSelection.initialize(configuration);
                    highestFitnessSelection.apply(population);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "RandomInitialization":
                try {
                    RandomInitialization randomInitialization = new RandomInitialization();
                    randomInitialization.initialize(configuration);
                    randomInitialization.apply(population);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "SwapMutation":
                try {
                    SwapMutation swapMutation = new SwapMutation();
                    swapMutation.initialize(configuration);
                    swapMutation.apply(population);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void runContinousOperator(String name) {
        switch (name){
            case "INITIATION":
                break;
            case "EVALUATION":
                break;
            case "SELECTION":
                break;
            case "CROSSING":
                break;
            case "MUTATION":
                break;
        }
    }

    private void runCombinatorialOperator(String name) {
        switch (name){
            case "INITIATION":
                break;
            case "EVALUATION":
                break;
            case "SELECTION":
                break;
            case "CROSSING":
                break;
            case "MUTATION":
                break;
        }
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("STARTED");
        this.problemType = "BINARY";
        this.iterations = 1;
        this.genotypeSize = 1;
        configInit();
        System.out.println("Config done");
        populationInit();
        System.out.println("population done");
        step(problemType, iterations);
}

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}