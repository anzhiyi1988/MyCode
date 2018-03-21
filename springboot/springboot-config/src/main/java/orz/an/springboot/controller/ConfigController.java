package orz.an.springboot.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConfigController {

    @Value("${my.name}")
    private String name;

    @Value("${my.age}")
    private int age;


    @RequestMapping(value = "/config")
    @ResponseBody
    public String config() {
        return name + " -> " + age;
    }


}
