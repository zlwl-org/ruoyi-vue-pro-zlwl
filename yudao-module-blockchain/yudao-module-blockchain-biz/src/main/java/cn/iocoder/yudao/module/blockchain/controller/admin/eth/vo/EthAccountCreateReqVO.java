package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("管理后台 - 以太坊账户创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EthAccountCreateReqVO extends EthAccountBaseVO {

}
