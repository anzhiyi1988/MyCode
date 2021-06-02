package orz.anzhy.stream;

import lombok.Data;

/**
 * SpxxSprVO
 * @description 审批人
 * @author anzhy
 * @date 2021/4/25 17:01
 * @version 1.0
 */
@Data
public class SpxxSprVO {
    private String code;
    private String name;
    private Integer order;
    private String zw; // 职务

    @Override
    public String toString() {
        return "name:" + name + ",zw:" + zw;
    }
}
