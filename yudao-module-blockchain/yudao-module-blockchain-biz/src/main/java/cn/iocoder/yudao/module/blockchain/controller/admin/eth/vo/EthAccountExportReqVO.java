package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 以太坊账户 Excel 导出 Request VO", description = "参数和 EthAccountPageReqVO 是一致的")
@Data
public class EthAccountExportReqVO {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "助记词")
    private String mnemonic;

    @ApiModelProperty(value = "私钥")
    private String privateKey;

    @ApiModelProperty(value = "归属")
    private Boolean owned;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

    @ApiModelProperty(value = "网络")
    private String net;

}
