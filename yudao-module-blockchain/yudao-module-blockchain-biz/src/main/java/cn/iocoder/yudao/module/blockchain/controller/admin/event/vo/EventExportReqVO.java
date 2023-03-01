package cn.iocoder.yudao.module.blockchain.controller.admin.event.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(name = "管理后台 - 事件 Excel 导出 Request VO", description = "参数和 EventPageReqVO 是一致的")
@Data
public class EventExportReqVO {

    @Schema(description = "主题")
    private String topic;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "网络")
    private String net;

    @Schema(description = "信息")
    private String info;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
