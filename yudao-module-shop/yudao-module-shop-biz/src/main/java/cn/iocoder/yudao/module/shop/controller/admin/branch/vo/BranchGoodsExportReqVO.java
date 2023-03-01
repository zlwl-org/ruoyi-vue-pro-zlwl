package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(name = "管理后台 - 门店商品 Excel 导出 Request VO", description = "参数和 BranchGoodsPageReqVO 是一致的")
@Data
public class BranchGoodsExportReqVO {

    @Schema(description = "商品名称")
    private String name;

    @Schema(description = "售价")
    private BigDecimal price;

    @Schema(description = "产品编号")
    private Long productId;

    @Schema(description = "店铺编号")
    private Long branchId;

    @Schema(description = "品牌编号")
    private Long brandId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
