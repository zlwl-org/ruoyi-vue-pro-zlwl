package cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "管理后台 - 网络 Excel 导出 Request VO", description = "参数和 NetPageReqVO 是一致的")
@Data
public class NetExportReqVO {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "中文名称")
    private String nameZh;

    @ApiModelProperty(value = "原生代币")
    private String symbol;

    @ApiModelProperty(value = "网络类型")
    private String type;

    @ApiModelProperty(value = "链ID")
    private Integer chainId;

}
