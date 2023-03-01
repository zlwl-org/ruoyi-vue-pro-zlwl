package cn.iocoder.yudao.module.blockchain.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 用户钱包创建 Request VO")
@Data
@EqualsAndHashCode()
@ToString(callSuper = true)
public class UserWalletImportReqVO {

    @Schema(description = "网络")
    private String net;

    @Schema(description = "助记词")
    private String mnemonic;

    @Schema(description = "私钥")
    private String privateKey;

    @Schema(description = "名称")
    private String name;

}
