package cn.iocoder.yudao.module.infra.controller.admin.proxy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Schema(description = "管理后台 - 网络代理 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProxyRespVO extends ProxyBaseVO {

    @Schema(description = "编号", required = true)
    private Long id;

    @Schema(description = "创建时间", required = true)
    private Date createTime;

}
