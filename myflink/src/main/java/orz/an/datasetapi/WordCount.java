package orz.an.datasetapi;

import org.apache.flink.api.common.functions.MapPartitionFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class WordCount {


    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSet<String> text = env.readTextFile("H:/logs/word.log");

        //DataSet<Tuple2<String, Integer>> rs = text.flatMap(new Splitter()).groupBy(0).sum(1);

        DataSet<Tuple2<String, Integer>> rs = text.flatMap(new Splitter());
        AggregateOperator<Tuple2<String, Integer>> ao = rs.groupBy(0).sum(1);
        //ao.print();
        ao.reduce((data1, data2) -> new Tuple2<String, Integer>(data1.f0 + data2.f0, data1.f1 + data2.f1))
                //.print();
                .writeAsText("H:/logs/anzhy.log");

        System.out.println(env.getExecutionPlan());
        // ao.filter(data -> data.f1 > 2).print();

        // MapOperator mo = ao.map(a -> a.f0);
        // mo.setParallelism(6).mapPartition(new MyMapPartion()).print();


    }


    public static class MyMapPartion implements MapPartitionFunction<String, Integer> {

        @Override
        public void mapPartition(Iterable<String> iterable, Collector<Integer> collector) {
            int c = 0;
            for (String s : iterable) {
                System.out.println(s);
                c++;
            }

            collector.collect(c);
        }
    }

}
