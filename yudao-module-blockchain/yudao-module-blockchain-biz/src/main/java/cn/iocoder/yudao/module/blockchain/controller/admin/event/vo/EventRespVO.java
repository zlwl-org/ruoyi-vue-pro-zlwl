package cn.iocoder.yudao.module.blockchain.controller.admin.event.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 事件 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EventRespVO extends EventBaseVO {

    @ApiModelProperty(value = "编号", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
