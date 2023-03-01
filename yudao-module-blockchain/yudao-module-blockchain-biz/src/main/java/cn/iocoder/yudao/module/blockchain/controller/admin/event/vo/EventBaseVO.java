package cn.iocoder.yudao.module.blockchain.controller.admin.event.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 事件 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class EventBaseVO {

    @Schema(description = "主题")
    private String topic;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "网络")
    private String net;

    @Schema(description = "信息")
    private String info;

}
