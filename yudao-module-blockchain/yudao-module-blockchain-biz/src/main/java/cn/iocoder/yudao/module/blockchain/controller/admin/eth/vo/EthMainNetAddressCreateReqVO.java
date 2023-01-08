package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 以太坊主网地址创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EthMainNetAddressCreateReqVO extends EthMainNetAddressBaseVO {

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "余额")
    private BigDecimal balance;

}
