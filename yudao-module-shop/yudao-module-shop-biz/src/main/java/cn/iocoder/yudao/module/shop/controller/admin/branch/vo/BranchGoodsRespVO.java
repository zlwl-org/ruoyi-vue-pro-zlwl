package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import cn.iocoder.yudao.module.shop.controller.admin.promotion.vo.PromotionRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Schema(description = "管理后台 - 门店商品 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BranchGoodsRespVO extends BranchGoodsBaseVO {

    @Schema(description = "商品编号", required = true)
    private Long id;

    @Schema(description = "创建时间", required = true)
    private Date createTime;

    private PromotionRespVO promotion;

}
