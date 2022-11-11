package cn.iocoder.yudao.module.shop.controller.admin.brand.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 商品品牌 Excel 导出 Request VO", description = "参数和 BrandPageReqVO 是一致的")
@Data
public class BrandExportReqVO {

    @ApiModelProperty(value = "品牌名称")
    private String name;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
