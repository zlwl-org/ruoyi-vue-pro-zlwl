package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - Eth区块数据创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EthBlockCreateReqVO extends EthBlockBaseVO {

}
