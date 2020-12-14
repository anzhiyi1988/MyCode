/**
 * @projectName poitest
 * @package orz.an.poitest.word
 * @className orz.an.poitest.word.Consts
 * @copyright anzhy.
 */
package orz.an.poitest.word;

import java.util.HashMap;
import java.util.Map;

/**
 * Consts
 *
 * @author anzhy
 * @version 1.0
 * @description 常量
 * @date 2020/5/7 19:24
 */
public class Code {


    public static final Map<String, String[]> CODE_MAP = new HashMap();

    static {
        CODE_MAP.put("GB/T 4762—1984", new String[]{"1", "2", "3"});
        CODE_MAP.put("GB/T 3304—1991", new String[]{"a", "b", "c"});
        CODE_MAP.put("GB/T 4763—2008", new String[]{"10", "20", "30"});
        CODE_MAP.put("GB/T 4658—2006", new String[]{"01", "02", "03"});
    }

}
