package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import lombok.*;
import java.util.*;
    import java.math.BigDecimal;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 会员 Excel 导出 Request VO", description = "参数和 ShopMemberPageReqVO 是一致的")
@Data
public class ShopMemberExportReqVO {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "销售员")
    private Long salesman;

    @ApiModelProperty(value = "客户类型")
    private Integer type;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "店铺编号")
    private Long branchId;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
