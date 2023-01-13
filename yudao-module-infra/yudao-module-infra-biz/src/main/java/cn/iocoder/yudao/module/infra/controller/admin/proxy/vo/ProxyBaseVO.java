package cn.iocoder.yudao.module.infra.controller.admin.proxy.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 网络代理 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ProxyBaseVO {

    @ApiModelProperty(value = "名字", required = true)
    @NotNull(message = "名字不能为空")
    private String name;

    @ApiModelProperty(value = "协议", required = true)
    @NotNull(message = "协议不能为空")
    private String protocol;

    @ApiModelProperty(value = "地址")
    private String ip;

    @ApiModelProperty(value = "端口")
    private Integer port;

    @ApiModelProperty(value = "密码验证", required = true)
    @NotNull(message = "密码验证不能为空")
    private Boolean auth;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

}
