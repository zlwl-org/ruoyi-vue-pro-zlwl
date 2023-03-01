package cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 网络创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NetCreateReqVO extends NetBaseVO {

    @Schema(description = "链ID")
    private Integer chainId;

}
