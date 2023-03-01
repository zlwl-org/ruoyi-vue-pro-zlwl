package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "管理后台 - 以太坊主网地址 Excel 导出 Request VO", description = "参数和 EthMainNetAddressPageReqVO 是一致的")
@Data
public class EthMainNetAddressExportReqVO {

    @Schema(description = "地址")
    private String address;

    @Schema(description = "标签")
    private String tags;

}
