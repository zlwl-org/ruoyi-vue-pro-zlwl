package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import cn.iocoder.yudao.module.shop.controller.admin.promotion.vo.PromotionRespVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@ApiModel("管理后台 - 门店商品 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BranchGoodsRespVO extends BranchGoodsBaseVO {

    @ApiModelProperty(value = "商品编号", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    private PromotionRespVO promotion;

}
