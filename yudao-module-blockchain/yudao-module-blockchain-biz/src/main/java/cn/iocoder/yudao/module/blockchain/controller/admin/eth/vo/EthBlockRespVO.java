package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - Eth区块数据 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EthBlockRespVO extends EthBlockBaseVO {

    @Schema(description = "编号", required = true)
    private Long id;

    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

}
