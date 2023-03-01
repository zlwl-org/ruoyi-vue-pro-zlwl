package cn.iocoder.yudao.module.demo.controller.admin.infra.net.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 区块链网络 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class NetBaseVO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "区块浏览器")
    private String explorer;

    @Schema(description = "原生代币")
    private String symbol;

    @Schema(description = "节点")
    private String endpoint;

    @Schema(description = "网络类型")
    private String type;

    @Schema(description = "信息")
    private String info;

}
