package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 门店出入库明细更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BranchStockItemUpdateReqVO extends BranchStockItemBaseVO {

    @Schema(description = "明细编号", required = true)
    @NotNull(message = "明细编号不能为空")
    private Long id;

}
