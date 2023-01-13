package cn.iocoder.yudao.module.shop.controller.admin.promotion.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 促销活动更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PromotionUpdateReqVO extends PromotionBaseVO {

    @ApiModelProperty(value = "活动编号", required = true)
    @NotNull(message = "活动编号不能为空")
    private Long id;

}
