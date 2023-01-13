package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 门店出入库更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BranchStockUpdateReqVO extends BranchStockBaseVO {

    @ApiModelProperty(value = "库存编号", required = true)
    @NotNull(message = "库存编号不能为空")
    private Long id;

}
