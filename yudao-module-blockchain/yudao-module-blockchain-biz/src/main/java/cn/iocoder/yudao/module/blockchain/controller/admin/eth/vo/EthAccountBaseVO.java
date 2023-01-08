package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* 以太坊账户 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class EthAccountBaseVO {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "助记词")
    private String mnemonic;

    @ApiModelProperty(value = "私钥")
    private String privateKey;

    @ApiModelProperty(value = "归属", required = true)
    @NotNull(message = "归属不能为空")
    private Boolean owned;

}
