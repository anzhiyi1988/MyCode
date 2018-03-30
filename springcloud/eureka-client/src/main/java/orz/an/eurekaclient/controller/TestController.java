package orz.an.eurekaclient.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {


    @Value("${server.port}")
    private String port;

    @RequestMapping("/hi")
    @ResponseBody
    public String hello(@RequestParam String name) {
        return "hi , i am " + name + " from port: " + port;
    }
}
