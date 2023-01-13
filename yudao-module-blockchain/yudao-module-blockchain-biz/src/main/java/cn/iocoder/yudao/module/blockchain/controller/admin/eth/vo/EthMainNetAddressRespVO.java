package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 以太坊主网地址 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EthMainNetAddressRespVO extends EthMainNetAddressBaseVO {

    @ApiModelProperty(value = "编号", required = true)
    private Long id;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

}
