package java8.lambda;

/*
 * SAM - Single Abstract Method
 * Qualquer interface que tenha apenas um método abstrato.
 */
@FunctionalInterface
public interface LambdaStrategy {
    public abstract int method(int one, int two);
}
