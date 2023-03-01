package cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Schema(description = "管理后台 - 网络 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NetRespVO extends NetBaseVO {

    @Schema(description = "编号", required = true)
    private Long id;

    @Schema(description = "链ID")
    private Integer chainId;

}
