package orz.anzhy.option;


import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class Process {

    @Setter
    @Getter
    private ClassA a;

    public void run() throws Exception {


        // String str = a.getB().getProp1();
        String str = Optional.ofNullable(a).map(ClassA::getB).map(ClassB::getProp1).orElseThrow(() -> new Exception("有空值"));

        System.out.println(str);
    }

}
