package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(name = "管理后台 - 门店出入库明细 Excel 导出 Request VO", description = "参数和 BranchStockItemPageReqVO 是一致的")
@Data
public class BranchStockItemExportReqVO {

    @Schema(description = "库存编号")
    private Long stockId;

    @Schema(description = "出入库类型")
    private String type;

    @Schema(description = "店铺编号")
    private Long branchId;

    @Schema(description = "商品编号")
    private Long productId;

    @Schema(description = "数量")
    private Integer amount;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

    @Schema(description = "产品名称")
    private String productName;

}
