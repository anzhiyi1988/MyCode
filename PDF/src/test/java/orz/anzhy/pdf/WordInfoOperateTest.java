/**
 * @projectName jdgtyypt-common
 * @package com.thunisoft.jdgtyypt.common.pdf
 * @className com.thunisoft.jdgtyypt.common.pdf.WordOperateTest
 * @copyright anzhy.
 */
package com.thunisoft.jdgtyypt.common.pdf;

import com.thunisoft.jdgtyypt.common.word.WordOperate;
import org.junit.Test;

/**
 * WordOperateTest
 *
 * @author anzhy
 * @version 1.0
 * @description
 * @date 2021/3/15 17:26
 */
public class WordInfoOperateTest {


    @Test
    public void testCove() {

        WordOperate wo = new WordOperate();
        try {
            wo.addCover();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
