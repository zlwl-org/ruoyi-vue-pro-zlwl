package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Schema(description = "管理后台 - 门店出入库 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BranchStockRespVO extends BranchStockBaseVO {

    @Schema(description = "库存编号", required = true)
    private Long id;

    @Schema(description = "创建者", required = true)
    private Long creator;

    @Schema(description = "创建时间", required = true)
    private Date createTime;

}
