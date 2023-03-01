package cn.iocoder.yudao.module.blockchain.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户钱包 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class UserWalletBaseVO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "地址")
    private String address;

}
