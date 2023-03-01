package cn.iocoder.yudao.module.demo.controller.admin.infra.endpoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 区块链节点 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class EndpointBaseVO {

    @Schema(description = "网络编号", required = true)
    @NotNull(message = "网络编号不能为空")
    private Long netId;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "地址")
    private String url;

    @Schema(description = "是否被墙", required = true)
    @NotNull(message = "是否被墙不能为空")
    private Boolean blocked;

    @Schema(description = "信息")
    private String info;

}
