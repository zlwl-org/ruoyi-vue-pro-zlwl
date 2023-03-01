package cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 网络 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class NetBaseVO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "中文名称")
    private String nameZh;

    @Schema(description = "原生代币")
    private String symbol;

    @Schema(description = "浏览器")
    private String explorer;

    @Schema(description = "默认节点")
    private String publicRpc;

    @Schema(description = "私密节点")
    private String privateRpc;

    @Schema(description = "网络类型")
    private String type;

}
