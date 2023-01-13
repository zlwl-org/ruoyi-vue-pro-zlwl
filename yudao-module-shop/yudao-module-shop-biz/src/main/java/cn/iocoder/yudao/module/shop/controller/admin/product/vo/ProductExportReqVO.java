package cn.iocoder.yudao.module.shop.controller.admin.product.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 产品 Excel 导出 Request VO", description = "参数和 ProductPageReqVO 是一致的")
@Data
public class ProductExportReqVO {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "门店编号")
    private Long branchId;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
