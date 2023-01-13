package cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo;

import io.swagger.annotations.ApiModelProperty;

public class NetRespSimpleVO {
    @ApiModelProperty(value = "编号", required = true)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "中文名称")
    private String nameZh;

    @ApiModelProperty(value = "原生代币")
    private String symbol;
}
