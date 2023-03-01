package cn.iocoder.yudao.module.demo.controller.admin.infra.net.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 区块链网络更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NetUpdateReqVO extends NetBaseVO {

    @Schema(description = "网络编号", required = true)
    @NotNull(message = "网络编号不能为空")
    private Long id;

}
