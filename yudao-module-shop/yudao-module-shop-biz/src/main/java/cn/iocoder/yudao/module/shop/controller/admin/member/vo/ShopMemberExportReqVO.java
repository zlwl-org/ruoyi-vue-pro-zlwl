package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(name = "管理后台 - 会员 Excel 导出 Request VO", description = "参数和 ShopMemberPageReqVO 是一致的")
@Data
public class ShopMemberExportReqVO {

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "销售员")
    private Long salesman;

    @Schema(description = "客户类型")
    private Integer type;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "店铺编号")
    private Long branchId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
