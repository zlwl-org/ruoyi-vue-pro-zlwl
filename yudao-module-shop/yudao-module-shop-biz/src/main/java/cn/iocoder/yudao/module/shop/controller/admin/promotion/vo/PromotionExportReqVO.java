package cn.iocoder.yudao.module.shop.controller.admin.promotion.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "管理后台 - 促销活动 Excel 导出 Request VO", description = "参数和 PromotionPageReqVO 是一致的")
@Data
public class PromotionExportReqVO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "促销类型")
    private String promotionType;

    @Schema(description = "状态")
    private Integer status;

}
