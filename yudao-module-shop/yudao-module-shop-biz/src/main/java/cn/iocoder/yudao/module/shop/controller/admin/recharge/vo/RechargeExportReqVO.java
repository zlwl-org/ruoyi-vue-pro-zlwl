package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 充值活动 Excel 导出 Request VO", description = "参数和 RechargePageReqVO 是一致的")
@Data
public class RechargeExportReqVO {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "充值金额")
    private BigDecimal price;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
