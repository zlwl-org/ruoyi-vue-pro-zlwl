package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 以太坊账户 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class EthAccountBaseVO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "助记词")
    private String mnemonic;

    @Schema(description = "私钥")
    private String privateKey;

    @Schema(description = "归属", required = true)
    @NotNull(message = "归属不能为空")
    private Boolean owned;

    @Schema(description = "网络")
    private String net;

}
