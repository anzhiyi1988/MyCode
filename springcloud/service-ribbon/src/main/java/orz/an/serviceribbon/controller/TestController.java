package orz.an.serviceribbon.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import orz.an.serviceribbon.service.TestService;

@Controller
public class TestController {

    @Autowired
    TestService testService;


    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello(@RequestParam String name) {
        System.out.println("------------------hello--------------------");
        return testService.hello(name);
    }

}
