package cn.iocoder.yudao.module.shop.controller.admin.promotion.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@ApiModel("管理后台 - 促销活动分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PromotionPageReqVO extends PageParam {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "促销类型")
    private String promotionType;

    @ApiModelProperty(value = "条件")
    private BigDecimal condition;

    @ApiModelProperty(value = "促销值")
    private BigDecimal target;

    @ApiModelProperty(value = "状态")
    private Integer status;

}
