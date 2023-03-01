package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 门店商品更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BranchGoodsUpdateReqVO extends BranchGoodsBaseVO {

    @Schema(description = "商品编号", required = true)
    @NotNull(message = "商品编号不能为空")
    private Long id;

}
