package orz.an.servicefeign.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import orz.an.servicefeign.service.FeignService;

@Controller
public class FeignController {


    @Autowired
    private FeignService feignService;

    @RequestMapping(value = "/hello" , method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam String name) {
        return feignService.hello(name);
    }

}
