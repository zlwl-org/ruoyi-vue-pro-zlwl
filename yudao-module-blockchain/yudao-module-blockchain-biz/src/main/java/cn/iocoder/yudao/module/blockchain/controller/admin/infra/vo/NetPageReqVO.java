package cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 网络分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NetPageReqVO extends PageParam {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "中文名称")
    private String nameZh;

    @Schema(description = "原生代币")
    private String symbol;

    @Schema(description = "网络类型")
    private String type;

    @Schema(description = "链ID")
    private Integer chainId;

}
