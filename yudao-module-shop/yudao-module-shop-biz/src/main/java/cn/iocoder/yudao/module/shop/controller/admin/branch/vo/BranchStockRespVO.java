package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@ApiModel("管理后台 - 门店出入库 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BranchStockRespVO extends BranchStockBaseVO {

    @ApiModelProperty(value = "库存编号", required = true)
    private Long id;

    @ApiModelProperty(value = "创建者", required = true)
    private Long creator;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
