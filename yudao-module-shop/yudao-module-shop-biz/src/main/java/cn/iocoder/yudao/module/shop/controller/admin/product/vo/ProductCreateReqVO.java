package cn.iocoder.yudao.module.shop.controller.admin.product.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 产品创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductCreateReqVO extends ProductBaseVO {

    @ApiModelProperty(value = "品牌编号")
    private Long brandId;

}
