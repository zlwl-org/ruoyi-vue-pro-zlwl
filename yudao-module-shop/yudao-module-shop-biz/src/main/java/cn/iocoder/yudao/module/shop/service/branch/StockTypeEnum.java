package cn.iocoder.yudao.module.shop.service.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StockTypeEnum {

    IN("in","入库"),
    OUT("out","出库");

    /** 类型 */
    private final String type;
    /** 名字 */
    private final String name;
}
