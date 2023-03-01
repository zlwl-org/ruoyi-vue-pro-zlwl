package cn.iocoder.yudao.module.shop.controller.admin.data.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Schema(description = "管理后台 - Shop Data Response VO")
@Data
@ToString(callSuper = true)
public class ShopDataRespVo {
    private Integer todayOrder;
    private Integer totalOrder;

    private BigDecimal todaySale;
    private BigDecimal totalSale;

    private Integer todayMember;
    private Integer totalMember;

    private BigDecimal todayRecharge;
    private BigDecimal totalRecharge;

    private BigDecimal todayConsume;
    private BigDecimal totalConsume;
}
