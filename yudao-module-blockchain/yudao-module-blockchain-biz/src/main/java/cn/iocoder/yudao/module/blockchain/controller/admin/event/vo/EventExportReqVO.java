package cn.iocoder.yudao.module.blockchain.controller.admin.event.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 事件 Excel 导出 Request VO", description = "参数和 EventPageReqVO 是一致的")
@Data
public class EventExportReqVO {

    @ApiModelProperty(value = "主题")
    private String topic;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "网络")
    private String net;

    @ApiModelProperty(value = "信息")
    private String info;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
