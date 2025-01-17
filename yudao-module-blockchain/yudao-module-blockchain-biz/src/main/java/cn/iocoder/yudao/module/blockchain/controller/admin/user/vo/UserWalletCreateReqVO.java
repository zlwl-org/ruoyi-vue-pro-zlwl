package cn.iocoder.yudao.module.blockchain.controller.admin.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("管理后台 - 用户钱包创建 Request VO")
@Data
@EqualsAndHashCode()
@ToString(callSuper = true)
public class UserWalletCreateReqVO {

    @ApiModelProperty(value = "网络")
    private String net;

    @ApiModelProperty(value = "名称")
    private String name;
}
