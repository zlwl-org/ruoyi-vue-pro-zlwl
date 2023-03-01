package cn.iocoder.yudao.module.infra.controller.admin.proxy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 网络代理 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProxyBaseVO {

    @Schema(description = "名字", required = true)
    @NotNull(message = "名字不能为空")
    private String name;

    @Schema(description = "协议", required = true)
    @NotNull(message = "协议不能为空")
    private String protocol;

    @Schema(description = "地址")
    private String ip;

    @Schema(description = "端口")
    private Integer port;

    @Schema(description = "密码验证", required = true)
    @NotNull(message = "密码验证不能为空")
    private Boolean auth;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

}
