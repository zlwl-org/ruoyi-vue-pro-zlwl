package cn.iocoder.yudao.module.blockchain.controller.admin.event.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;


@Schema(description = "管理后台 - 事件 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EventRespVO extends EventBaseVO {

    @Schema(description = "编号", required = true)
    private Long id;

    @Schema(description = "创建时间", required = true)
    private Date createTime;

}
