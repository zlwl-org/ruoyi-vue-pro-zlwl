package cn.iocoder.yudao.module.shop.controller.admin.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;


@Schema(description = "管理后台 - 产品 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductRespVO extends ProductBaseVO {

    @Schema(description = "产品编号", required = true)
    private Long id;

    @Schema(description = "品牌名称")
    private String brandName;

    @Schema(description = "创建时间", required = true)
    private Date createTime;

}
