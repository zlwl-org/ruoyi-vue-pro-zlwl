package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("管理后台 - 会员账户流水创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberAccountLogCreateReqVO extends MemberAccountLogBaseVO {

}
