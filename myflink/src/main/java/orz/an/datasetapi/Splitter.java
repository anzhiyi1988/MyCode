package orz.an.datasetapi;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class Splitter implements FlatMapFunction<String, Tuple2<String, Integer>> {


    @Override
    public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) {

        for (String word : s.split(" ")) {
            collector.collect(new Tuple2<>(word.toLowerCase(), 1));
        }
    }
}
