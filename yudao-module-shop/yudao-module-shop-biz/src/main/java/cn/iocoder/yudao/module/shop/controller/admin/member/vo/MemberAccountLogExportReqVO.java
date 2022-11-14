package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 会员账户流水 Excel 导出 Request VO", description = "参数和 MemberAccountLogPageReqVO 是一致的")
@Data
public class MemberAccountLogExportReqVO {

    @ApiModelProperty(value = "会员编号")
    private Long memberId;

    @ApiModelProperty(value = "发生方式")
    private String action;

    @ApiModelProperty(value = "充值余额变动")
    private BigDecimal balance;

    @ApiModelProperty(value = "赠送余额变动")
    private BigDecimal gift;

    @ApiModelProperty(value = "积分变动")
    private BigDecimal point;

    @ApiModelProperty(value = "成长值变动")
    private BigDecimal growth;

    @ApiModelProperty(value = "信息")
    private String info;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
