package orz.an.design.pattern.interpreter.demo;

import java.util.HashMap;

public class AddExpression extends SymbolExpression {

    public AddExpression(Expression left, Expression right) {
        super(left,right);
    }


    public int interpreter(HashMap<String, Integer> var) {
        return left.interpreter(var) +right.interpreter(var);
    }
}
