/**
 * @projectName Test
 * @package orz.anzhy.serializable
 * @className orz.anzhy.serializable.Student
 * @copyright anzhy.
 */
package orz.anzhy.serializable;

import lombok.Data;

import java.io.Serializable;

/**
 * Student
 *
 * @author anzhy
 * @version 1.0
 * @description
 * @date 2021/2/23 10:16
 */
@Data
public class Student implements Serializable {

    private Integer age;
    private String name;

}
