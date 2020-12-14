package orz.an.poitest.word;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
public class JsonHandler {

    public void parseData2Json(Map<String, List<String[]>> data) {
        data.forEach((k, v) -> {
            StandardJsonBean bean = new StandardJsonBean();
            bean.setTitle(k);
            bean.setDescription(k);

            List<String> list = new ArrayList<>();
            Map<String, Map<String, Object>> map = new HashMap<>();
            v.forEach(line -> {
                String colName = StringUtils.lowerCase(line[2]);
                list.add(colName);
                map.put(colName, getColumnMap(line));
            });
            bean.getItems().put("required", list);
            bean.getItems().put("properties", map);

            log.info(JSON.toJSONString(bean));
        });
    }

    private Map<String, Object> getColumnMap(String[] line) {
        Map<String, Object> m = new HashMap<>();
        m.put("description", line[1]);
        String type = line[3];

        m.put("type", "string");
        switch (type) {
            case "D":
                m.put("format", "date");
                break;
            case "DT":
                m.put("format", "full-date");
                break;
            case "BB":
            case "Cdx":
            case "C":
                break;
            case "N":
                m.put("type", "integer");
                break;
            case "M":
            case "F":
                m.put("type", "number");
                break;
            default:

                Pattern p = Pattern.compile("GB/T\\s[0-9]{4}—[0-9]{4}");
                Matcher matcher = p.matcher(line[4]);
                if (matcher.find()) {
                    log.debug(matcher.group());
                    m.remove("type");
                    m.put("enum", Code.CODE_MAP.get(matcher.group()));
                } else {
                    String len = type.substring(1);
                    if (StringUtils.isNumeric(len)) {
                        m.put("maxLength", Integer.parseInt(len));
                    }
                }
        }
        return m;
    }
}
