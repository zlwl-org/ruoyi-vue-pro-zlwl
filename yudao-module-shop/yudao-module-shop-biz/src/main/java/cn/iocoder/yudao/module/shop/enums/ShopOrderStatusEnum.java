package cn.iocoder.yudao.module.shop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShopOrderStatusEnum {

    WAITING("waiting", "挂单"),
    DONE("done", "已完成"),
    CANCELED("canceled", "已取消"),
    UNPAID("unpaid", "待付款");

    private String status;
    private String name;
}
