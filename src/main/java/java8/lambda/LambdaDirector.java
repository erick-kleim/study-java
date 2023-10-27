package java8.lambda;

public class LambdaDirector {

    LambdaStrategy contextLambda;

    public LambdaDirector(LambdaStrategy contextLambda){
       this.contextLambda = contextLambda;
    }

    public LambdaDirector() {
    }

    public int doLambda(int one, int two){
        return contextLambda.method(one,two);
    }

}
