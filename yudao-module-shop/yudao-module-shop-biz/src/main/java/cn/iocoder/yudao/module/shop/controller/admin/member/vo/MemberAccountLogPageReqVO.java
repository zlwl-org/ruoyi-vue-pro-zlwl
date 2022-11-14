package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 会员账户流水分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberAccountLogPageReqVO extends PageParam {

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
