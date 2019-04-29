package pl.edu.agh.iisg.ewolucja.core;

public interface Operator {

    void initialize(Configuration configuration);

    Population apply(Population population);

    ProblemType getType();
}
