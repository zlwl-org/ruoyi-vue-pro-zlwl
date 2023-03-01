package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(name = "管理后台 - 以太坊账户 Excel 导出 Request VO", description = "参数和 EthAccountPageReqVO 是一致的")
@Data
public class EthAccountExportReqVO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "助记词")
    private String mnemonic;

    @Schema(description = "私钥")
    private String privateKey;

    @Schema(description = "归属")
    private Boolean owned;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

    @Schema(description = "网络")
    private String net;

}
