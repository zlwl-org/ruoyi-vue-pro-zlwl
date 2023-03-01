package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 会员账户流水分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberAccountLogPageReqVO extends PageParam {

    @Schema(description = "会员编号")
    private Long memberId;

    @Schema(description = "发生方式")
    private String action;

    @Schema(description = "充值余额变动")
    private BigDecimal balance;

    @Schema(description = "赠送余额变动")
    private BigDecimal gift;

    @Schema(description = "积分变动")
    private BigDecimal point;

    @Schema(description = "成长值变动")
    private BigDecimal growth;

    @Schema(description = "信息")
    private String info;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
