package cn.iocoder.yudao.module.shop.controller.admin.brand.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 商品品牌更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BrandUpdateReqVO extends BrandBaseVO {

    @ApiModelProperty(value = "品牌编号", required = true)
    @NotNull(message = "品牌编号不能为空")
    private Long id;

    @ApiModelProperty(value = "品牌描述")
    private String description;

}
