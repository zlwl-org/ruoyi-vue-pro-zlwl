package cn.iocoder.yudao.module.shop.service.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShopOrderPayStatusEnum {

    UNPAID("unpaid", "未支付"),
    PAID("paid", "已支付"),
    FAILED("failed", "支付失败");

    private String status;
    private String name;
}
