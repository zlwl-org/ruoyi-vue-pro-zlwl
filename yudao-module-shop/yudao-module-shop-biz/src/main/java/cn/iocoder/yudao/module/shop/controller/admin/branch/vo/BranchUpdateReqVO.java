package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 门店更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BranchUpdateReqVO extends BranchBaseVO {

    @ApiModelProperty(value = "门店编号", required = true)
    @NotNull(message = "门店编号不能为空")
    private Long id;

}
