package cn.iocoder.yudao.module.shop.controller.admin.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(name = "管理后台 - 产品 Excel 导出 Request VO", description = "参数和 ProductPageReqVO 是一致的")
@Data
public class ProductExportReqVO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "门店编号")
    private Long branchId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
