package cn.iocoder.yudao.module.demo.controller.admin.infra.net.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.*;

@Schema(description = "管理后台 - 区块链网络 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NetRespVO extends NetBaseVO {

    @Schema(description = "网络编号", required = true)
    private Long id;

    @Schema(description = "创建时间", required = true)
    private Date createTime;

}
