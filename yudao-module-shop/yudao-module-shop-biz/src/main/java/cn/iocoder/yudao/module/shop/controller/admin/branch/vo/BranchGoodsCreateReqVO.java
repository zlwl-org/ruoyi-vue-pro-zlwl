package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 门店商品创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BranchGoodsCreateReqVO extends BranchGoodsBaseVO {

}
