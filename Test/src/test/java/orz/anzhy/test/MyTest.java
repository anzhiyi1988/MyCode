package orz.anzhy.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import orz.anzhy.code.Code;
import orz.anzhy.option.Process;
import orz.anzhy.stream.SpxxSprVO;
import orz.anzhy.test.boolen.HasException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MyTest {


    @Test
    public void testBoolen() {
        System.out.println("------------------------");
        System.out.println(HasException.isOpen(1));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------");
        System.out.println(HasException.isOpen(0));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------");
        System.out.println(HasException.isOpen(2));
    }


    @Test
    public void testOption() {


        Process p = new Process();

        try {
            p.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 学习枚举类型，之前一直没有好好的去做测试
     */
    @Test
    public void testEnumType() {
        Code c = Code.C1;
        System.out.println(c.name());
        System.out.println(c.getValue());
        System.out.println(c.getName());

        System.out.println(Code.values()[2]);
    }


    @Test
    public void testSubString() {

        String path = "F:\\opt\\thunisoft\\jdgtyypt\\data\\wfs\\2021\\02\\06\\e116ff8a4ad341da8cfd480491669991/wj/xxx.pdf";
        String newPath = path.substring(0, path.lastIndexOf("wj"));
        System.out.println(newPath);
        cn.hutool.core.io.FileUtil.del(newPath);

    }

    @Test
    public void testDelFile() throws IOException {

        String path = "F:\\opt\\thunisoft\\jdgtyypt\\data\\wfs\\2021\\02\\06\\e116ff8a4ad341da8cfd480491669991/wj/xxx.pdf";
        Files.delete(Paths.get(path));

    }

    @Test
    public void testDateF() {
        String s = "2021-04-15 16:21:57.883035";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date data = simpleDateFormat.parse(s);
            s = simpleDateFormat.format(data);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJson() {
        Map<String, String> extinfo = new HashMap<>();
        extinfo.put("startTime", "1");
        extinfo.put("endTime", "2");
        extinfo.put("deptIds", "p.getDeptIds()");
        extinfo.put("deptNames", "p.getDeptNames()");
        extinfo.put("bgid", null);
        System.out.println(JSON.toJSONString(extinfo));

    }


    @Test
    public void testStream() {

        SpxxSprVO v1 = new SpxxSprVO();
        v1.setCode("5");
        v1.setName("name5");
        v1.setOrder(5);


        SpxxSprVO v2 = new SpxxSprVO();
        v2.setCode("4");
        v2.setName("name4");
        v2.setOrder(4);

        SpxxSprVO v3 = new SpxxSprVO();
        v3.setCode("3");
        v3.setName("name3");
        v3.setOrder(3);
        // v3.setZw("23");

        SpxxSprVO v4 = new SpxxSprVO();
        v4.setCode("2");
        v4.setName("name2");
        v4.setOrder(2);

        SpxxSprVO v5 = new SpxxSprVO();
        v5.setCode("1");
        v5.setName("name1");
        v5.setOrder(1);

        List<SpxxSprVO> list = new ArrayList<>();
        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        list.add(v5);

        System.out.println("===========================");
        List<SpxxSprVO> list1 = list.stream()
                .filter(spxxSprVO -> StringUtils.equals(spxxSprVO.getZw(), "23"))
                .sorted(Comparator.comparingInt(SpxxSprVO::getOrder))
                .collect(Collectors.toList());
        list1.forEach(System.out::println);
        System.out.println("===========================");
        List<SpxxSprVO> list2 = list.stream()
                .filter(spxxSprVO -> !StringUtils.equals(spxxSprVO.getZw(), "23"))
                .sorted(Comparator.comparingInt(SpxxSprVO::getOrder))
                .collect(Collectors.toList());
        list2.forEach(System.out::println);
        System.out.println("===========================");

        list1.addAll(list2);

        list1.forEach(System.out::println);


    }

    @Test
    public void testSplit() {
        String str = "安致宜";
        String[] sprmc = str.split("-");
        System.out.println(sprmc[sprmc.length - 1]);
        System.out.println(str);

    }

    @Test
    public void strContent(){

        System.out.println( StringUtils.containsAny("edit","edit","merge") );
        System.out.println( StringUtils.containsAny("merge","edit","merge") );
        System.out.println( StringUtils.containsAny("del","edit","merge") );

    }

}
