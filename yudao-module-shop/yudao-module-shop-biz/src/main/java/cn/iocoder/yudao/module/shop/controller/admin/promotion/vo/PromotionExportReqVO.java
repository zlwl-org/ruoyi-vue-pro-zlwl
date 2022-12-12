package cn.iocoder.yudao.module.shop.controller.admin.promotion.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@ApiModel(value = "管理后台 - 促销活动 Excel 导出 Request VO", description = "参数和 PromotionPageReqVO 是一致的")
@Data
public class PromotionExportReqVO {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "促销类型")
    private String promotionType;

    @ApiModelProperty(value = "状态")
    private Integer status;

}
