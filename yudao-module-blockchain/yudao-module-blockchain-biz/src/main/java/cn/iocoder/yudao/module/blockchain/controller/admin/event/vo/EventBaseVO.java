package cn.iocoder.yudao.module.blockchain.controller.admin.event.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 事件 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class EventBaseVO {

    @ApiModelProperty(value = "主题")
    private String topic;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "网络")
    private String net;

    @ApiModelProperty(value = "信息")
    private String info;

}
