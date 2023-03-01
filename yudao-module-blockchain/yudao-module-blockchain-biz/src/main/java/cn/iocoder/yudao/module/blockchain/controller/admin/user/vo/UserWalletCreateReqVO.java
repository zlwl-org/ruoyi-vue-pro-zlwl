package cn.iocoder.yudao.module.blockchain.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 用户钱包创建 Request VO")
@Data
@EqualsAndHashCode()
@ToString(callSuper = true)
public class UserWalletCreateReqVO {

    @Schema(description = "网络")
    private String net;

    @Schema(description = "名称")
    private String name;
}
