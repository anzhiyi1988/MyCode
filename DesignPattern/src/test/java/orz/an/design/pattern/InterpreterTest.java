package orz.an.design.pattern;

import org.junit.Test;
import orz.an.design.pattern.interpreter.demo.AddExpression;
import orz.an.design.pattern.interpreter.demo.Expression;
import orz.an.design.pattern.interpreter.demo.SubExpression;
import orz.an.design.pattern.interpreter.demo.VarExpression;

import java.util.HashMap;
import java.util.Stack;

public class InterpreterTest {


    @Test
    public void interTest() {


        String expStr = "a+b-c";


        Stack<Expression> stack = new Stack();

        char[] cArr = expStr.toCharArray();

        Expression left;
        Expression right;
        Expression symbol;

        for (int i = 0; i < cArr.length; i++) {

            switch (cArr[i]) {
                case '+':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(cArr[++i]));
                    symbol = new AddExpression(left, right);
                    stack.push(symbol);
                    break;
                case '-':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(cArr[++i]));
                    symbol = new SubExpression(left, right);
                    stack.push(symbol);
                    break;
                default:
                    stack.push(new VarExpression(String.valueOf(cArr[i])));
            }
        }


        HashMap<String, Integer> var = new HashMap();
        var.put("a", 1);
        var.put("b", 2);
        var.put("c", 3);

        int result = stack.pop().interpreter(var);
        System.out.println("结果：" + result);

    }


}
