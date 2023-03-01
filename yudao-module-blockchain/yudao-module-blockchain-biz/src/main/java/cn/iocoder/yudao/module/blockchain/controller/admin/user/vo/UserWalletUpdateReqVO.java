package cn.iocoder.yudao.module.blockchain.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 用户钱包更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserWalletUpdateReqVO extends UserWalletBaseVO {

    @Schema(description = "钱包编号", required = true)
    @NotNull(message = "钱包编号不能为空")
    private Long id;

}
