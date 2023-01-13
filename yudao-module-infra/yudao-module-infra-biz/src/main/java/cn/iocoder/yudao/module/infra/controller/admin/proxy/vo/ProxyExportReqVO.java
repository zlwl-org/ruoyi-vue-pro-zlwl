package cn.iocoder.yudao.module.infra.controller.admin.proxy.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 网络代理 Excel 导出 Request VO", description = "参数和 ProxyPageReqVO 是一致的")
@Data
public class ProxyExportReqVO {

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "协议")
    private String protocol;

    @ApiModelProperty(value = "地址")
    private String ip;

    @ApiModelProperty(value = "端口")
    private Integer port;

    @ApiModelProperty(value = "密码验证")
    private Boolean auth;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
