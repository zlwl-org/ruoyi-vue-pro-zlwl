package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 门店订单明细更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopOrderItemUpdateReqVO extends ShopOrderItemBaseVO {

    @Schema(description = "明细编号", required = true)
    @NotNull(message = "明细编号不能为空")
    private Long id;

}
