package cn.iocoder.yudao.module.blockchain.controller.admin.event.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 事件更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EventUpdateReqVO extends EventBaseVO {

    @Schema(description = "编号", required = true)
    @NotNull(message = "编号不能为空")
    private Long id;

}
