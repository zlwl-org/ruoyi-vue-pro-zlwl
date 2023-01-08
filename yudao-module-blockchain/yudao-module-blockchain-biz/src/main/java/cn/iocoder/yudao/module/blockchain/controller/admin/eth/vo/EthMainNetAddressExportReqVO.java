package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@ApiModel(value = "管理后台 - 以太坊主网地址 Excel 导出 Request VO", description = "参数和 EthMainNetAddressPageReqVO 是一致的")
@Data
public class EthMainNetAddressExportReqVO {

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "标签")
    private String tags;

}
