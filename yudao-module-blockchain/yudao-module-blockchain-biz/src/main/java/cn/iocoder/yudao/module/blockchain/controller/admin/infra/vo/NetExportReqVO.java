package cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "管理后台 - 网络 Excel 导出 Request VO", description = "参数和 NetPageReqVO 是一致的")
@Data
public class NetExportReqVO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "中文名称")
    private String nameZh;

    @Schema(description = "原生代币")
    private String symbol;

    @Schema(description = "网络类型")
    private String type;

    @Schema(description = "链ID")
    private Integer chainId;

}
