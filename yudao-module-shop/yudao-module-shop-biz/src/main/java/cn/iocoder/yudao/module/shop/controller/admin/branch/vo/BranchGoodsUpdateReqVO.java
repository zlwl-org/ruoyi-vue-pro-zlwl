package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 门店商品更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BranchGoodsUpdateReqVO extends BranchGoodsBaseVO {

    @ApiModelProperty(value = "商品编号", required = true)
    @NotNull(message = "商品编号不能为空")
    private Long id;

}
