package cn.iocoder.yudao.module.shop.controller.admin.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 产品更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductUpdateReqVO extends ProductBaseVO {

    @Schema(description = "产品编号", required = true)
    @NotNull(message = "产品编号不能为空")
    private Long id;

    @Schema(description = "品牌编号")
    private Long brandId;

}
