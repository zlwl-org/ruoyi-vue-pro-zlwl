package cn.iocoder.yudao.module.demo.controller.admin.infra.net.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.*;


import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 区块链网络分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NetPageReqVO extends PageParam {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "区块浏览器")
    private String explorer;

    @Schema(description = "原生代币")
    private String symbol;

    @Schema(description = "节点")
    private String endpoint;

    @Schema(description = "网络类型")
    private String type;

    @Schema(description = "信息")
    private String info;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
