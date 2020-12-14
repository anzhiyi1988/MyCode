/**
 * @projectName flinktableapitest
 * @package orz.an.flinktableapitest
 * @className orz.an.flinktableapitest.MyTest
 * @copyright anzhy.
 */
package orz.an.flinktableapitest;

import org.apache.flink.api.java.ExecutionEnvironment;

/**
 * MyTest
 * @description 我自己的测试
 * @author anzhy
 * @date 2020/9/3 16:54
 * @version 1.0
 */
public class MyTest {

    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env   = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment tableEnv = BatchTableEnvironment.create(env);


    }

}
