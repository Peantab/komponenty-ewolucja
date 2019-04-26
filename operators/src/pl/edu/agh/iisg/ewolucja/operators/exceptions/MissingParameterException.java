package pl.edu.agh.iisg.ewolucja.operators.exceptions;

public class MissingParameterException extends RuntimeException {
    public MissingParameterException(String parameterName) {
        super("Missing parameter '" + parameterName + "' in configuration");
    }
}
