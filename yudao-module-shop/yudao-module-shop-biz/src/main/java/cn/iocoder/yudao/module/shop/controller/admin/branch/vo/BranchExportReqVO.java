package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(name = "管理后台 - 门店 Excel 导出 Request VO", description = "参数和 BranchPageReqVO 是一致的")
@Data
public class BranchExportReqVO {

    @Schema(description = "名称", example = "总店")
    private String name;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "电话")
    private String tel;

    @Schema(description = "状态", example = "0")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
