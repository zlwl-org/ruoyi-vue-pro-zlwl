package cn.iocoder.yudao.module.shop.controller.admin.data.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@ApiModel("管理后台 - Shop Data Response VO")
@Data
@ToString(callSuper = true)
public class ShopDataRespVo {
    private Integer todayOrder;
    private BigDecimal todaySale;
    private Integer todayMember;
    private BigDecimal todayRecharge;
    private Integer totalOrder;
    private BigDecimal totalSale;
    private Integer totalMember;
    private BigDecimal totalRecharge;
}
