package cn.iocoder.yudao.module.demo.controller.admin.infra.endpoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.*;


@Schema(description = "管理后台 - 区块链节点 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EndpointRespVO extends EndpointBaseVO {

    @Schema(description = "节点编号", required = true)
    private Long id;

    @Schema(description = "创建时间", required = true)
    private Date createTime;

}
