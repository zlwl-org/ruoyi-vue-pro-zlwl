package cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@ApiModel("管理后台 - 网络分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NetPageReqVO extends PageParam {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "中文名称")
    private Integer nameZh;

    @ApiModelProperty(value = "原生代币")
    private String symbol;

    @ApiModelProperty(value = "网络类型")
    private String type;

    @ApiModelProperty(value = "链ID")
    private Integer chainId;

}
