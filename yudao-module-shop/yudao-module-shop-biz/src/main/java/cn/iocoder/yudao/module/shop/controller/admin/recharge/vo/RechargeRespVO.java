package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import lombok.*;
import java.util.*;
    import java.math.BigDecimal;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 充值活动 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RechargeRespVO extends RechargeBaseVO {

    @ApiModelProperty(value = "活动编号", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
