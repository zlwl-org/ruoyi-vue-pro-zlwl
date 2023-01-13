package cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 网络创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NetCreateReqVO extends NetBaseVO {

    @ApiModelProperty(value = "链ID")
    private Integer chainId;

}
