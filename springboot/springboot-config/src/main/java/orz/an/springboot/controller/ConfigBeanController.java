package orz.an.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import orz.an.springboot.bean.ConfigBean;
import orz.an.springboot.bean.PropBean;

@Controller
@EnableConfigurationProperties({ConfigBean.class, PropBean.class})
public class ConfigBeanController {

    @Autowired
    private ConfigBean configBean;

    @Autowired
    private PropBean propBean;


    @RequestMapping(value = "/configbean")
    @ResponseBody
    public String configBean(){

        return configBean.getName()+" >>>>"+ configBean.getUuid()+" >>>>"+configBean.getMax();

    }

    @RequestMapping(value = "/propbean")
    @ResponseBody
    public String propBean(){

        return propBean.getName()+" >>>>"+ propBean.getAge();

    }


}
