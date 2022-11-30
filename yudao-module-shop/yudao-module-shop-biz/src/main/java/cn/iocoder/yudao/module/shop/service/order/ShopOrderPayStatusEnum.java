package cn.iocoder.yudao.module.shop.service.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShopOrderPayStatusEnum {

    UNPAID("unpaid", "未支付"),
    PAID("paid", "已支付"),
    PART_PAID("part_paid", "部分支付"),
    PART_REFUND("part_refund", "部分退款"),
    REFUND("refund", "已退款"),
    FAILED("failed", "支付失败");

    /** 支付状态 */
    private final String status;
    /** 名字 */
    private final String name;
}
