/**
 * @projectName poitest
 * @package orz.an.poitest.word
 * @className orz.an.poitest.word.StandardJsonBean
 * @copyright anzhy.
 */
package orz.an.poitest.word;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * StandardJsonBean
 *
 * @author anzhy
 * @version 1.0
 * @description json规范实体
 * @date 2020/4/29 18:50
 */

@Data
public class StandardJsonBean {

    @Setter(AccessLevel.NONE)
    private String schema;

    private String title;
    private String description;

    @Setter(AccessLevel.NONE)
    private String type;

    @Setter(AccessLevel.NONE)
    private Map<String, Object> items;


    StandardJsonBean() {
        items = new HashMap<>();
        items.put("type", "object");

        this.type = "array";
        this.schema = "http://json-schema.org/draft-07/schema#";
    }

}
