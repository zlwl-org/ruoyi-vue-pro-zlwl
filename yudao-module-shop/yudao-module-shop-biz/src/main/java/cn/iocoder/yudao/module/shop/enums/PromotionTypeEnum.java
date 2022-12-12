package cn.iocoder.yudao.module.shop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PromotionTypeEnum {

    AMOUNT("amount_promotion","买送"),
    PRICE("price_promotion","满减");

    /** 类型 */
    private final String type;
    /** 名字 */
    private final String name;
}
