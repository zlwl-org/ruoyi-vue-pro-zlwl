package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 门店出入库明细更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BranchStockItemUpdateReqVO extends BranchStockItemBaseVO {

    @ApiModelProperty(value = "明细编号", required = true)
    @NotNull(message = "明细编号不能为空")
    private Long id;

}
