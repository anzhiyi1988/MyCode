package orz.an.datasetapi;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.IterativeDataSet;

public class Pi {
    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        IterativeDataSet<Integer> ids = env.fromElements(0).iterate(100);
        //ids.print();

        DataSet<Integer> flags = ids.map((MapFunction<Integer, Integer>) i -> {
            double x = Math.random();
            double y = Math.random();
            System.out.println("就是这么实现计数的：" + i);
            return i + ((x * x + y * y > 1 ? 1 : 0));
        });


        DataSet<Integer> count = ids.closeWith(flags);

        count.print();

    }


}
