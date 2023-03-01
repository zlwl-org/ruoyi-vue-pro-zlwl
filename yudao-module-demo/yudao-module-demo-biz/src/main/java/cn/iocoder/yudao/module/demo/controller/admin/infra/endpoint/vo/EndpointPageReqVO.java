package cn.iocoder.yudao.module.demo.controller.admin.infra.endpoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.*;


import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 区块链节点分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EndpointPageReqVO extends PageParam {

    @Schema(description = "网络编号")
    private Long netId;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "地址")
    private String url;

    @Schema(description = "是否被墙")
    private Boolean blocked;

    @Schema(description = "信息")
    private String info;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
