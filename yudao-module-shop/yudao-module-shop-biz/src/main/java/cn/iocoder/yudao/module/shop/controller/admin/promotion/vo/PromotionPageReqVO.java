package cn.iocoder.yudao.module.shop.controller.admin.promotion.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 促销活动分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PromotionPageReqVO extends PageParam {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "促销类型")
    private String promotionType;

    @Schema(description = "状态")
    private Integer status;

}
