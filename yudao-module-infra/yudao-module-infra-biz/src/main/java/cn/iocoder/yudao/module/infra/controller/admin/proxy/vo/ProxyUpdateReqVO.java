package cn.iocoder.yudao.module.infra.controller.admin.proxy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 网络代理更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProxyUpdateReqVO extends ProxyBaseVO {

    @Schema(description = "编号", required = true)
    @NotNull(message = "编号不能为空")
    private Long id;

}
