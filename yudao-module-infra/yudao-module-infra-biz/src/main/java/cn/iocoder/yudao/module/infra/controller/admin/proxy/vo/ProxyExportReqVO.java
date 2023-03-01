package cn.iocoder.yudao.module.infra.controller.admin.proxy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 网络代理 Excel 导出 Request VO")
@Data
public class ProxyExportReqVO {

    @Schema(description = "名字")
    private String name;

    @Schema(description = "协议")
    private String protocol;

    @Schema(description = "地址")
    private String ip;

    @Schema(description = "端口")
    private Integer port;

    @Schema(description = "密码验证")
    private Boolean auth;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
