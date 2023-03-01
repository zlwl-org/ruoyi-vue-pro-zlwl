package cn.iocoder.yudao.module.demo.controller.admin.infra.endpoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 区块链节点更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EndpointUpdateReqVO extends EndpointBaseVO {

    @Schema(description = "节点编号", required = true)
    @NotNull(message = "节点编号不能为空")
    private Long id;

}
