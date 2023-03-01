package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 以太坊主网地址分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EthMainNetAddressPageReqVO extends PageParam {

    @Schema(description = "地址")
    private String address;

    @Schema(description = "标签")
    private String tags;

}
