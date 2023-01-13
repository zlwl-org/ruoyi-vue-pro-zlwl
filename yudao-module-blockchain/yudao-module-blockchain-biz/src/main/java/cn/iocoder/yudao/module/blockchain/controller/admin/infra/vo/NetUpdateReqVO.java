package cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 网络更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NetUpdateReqVO extends NetBaseVO {

    @ApiModelProperty(value = "编号", required = true)
    @NotNull(message = "编号不能为空")
    private Long id;

}
