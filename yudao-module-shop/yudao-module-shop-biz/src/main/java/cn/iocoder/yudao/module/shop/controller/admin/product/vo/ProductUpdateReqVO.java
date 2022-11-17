package cn.iocoder.yudao.module.shop.controller.admin.product.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 产品更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductUpdateReqVO extends ProductBaseVO {

    @ApiModelProperty(value = "产品编号", required = true)
    @NotNull(message = "产品编号不能为空")
    private Long id;

    @ApiModelProperty(value = "品牌编号")
    private Long brandId;

}
