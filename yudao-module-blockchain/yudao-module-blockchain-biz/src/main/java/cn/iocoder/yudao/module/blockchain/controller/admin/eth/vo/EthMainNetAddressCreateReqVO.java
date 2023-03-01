package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 以太坊主网地址创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EthMainNetAddressCreateReqVO extends EthMainNetAddressBaseVO {

    @Schema(description = "地址")
    private String address;

    @Schema(description = "余额")
    private BigDecimal balance;

}
