package cn.iocoder.yudao.module.shop.controller.admin.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 产品创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductCreateReqVO extends ProductBaseVO {

    @Schema(description = "品牌编号")
    private Long brandId;

}
