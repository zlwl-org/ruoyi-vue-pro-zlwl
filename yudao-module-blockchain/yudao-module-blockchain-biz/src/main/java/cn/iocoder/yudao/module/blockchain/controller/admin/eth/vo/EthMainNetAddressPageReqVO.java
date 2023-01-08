package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@ApiModel("管理后台 - 以太坊主网地址分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EthMainNetAddressPageReqVO extends PageParam {

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "标签")
    private String tags;

}
