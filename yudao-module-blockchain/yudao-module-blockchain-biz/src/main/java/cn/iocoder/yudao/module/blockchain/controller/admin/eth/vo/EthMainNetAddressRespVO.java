package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;


@Schema(description = "管理后台 - 以太坊主网地址 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EthMainNetAddressRespVO extends EthMainNetAddressBaseVO {

    @Schema(description = "编号", required = true)
    private Long id;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "余额")
    private BigDecimal balance;

    @Schema(description = "更新时间", required = true)
    private Date updateTime;

}
