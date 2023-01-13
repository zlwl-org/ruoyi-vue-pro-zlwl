package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 门店商品 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class BranchGoodsBaseVO {

    @ApiModelProperty(value = "商品名称", required = true)
    @NotNull(message = "商品名称不能为空")
    private String name;

    @ApiModelProperty(value = "售价", required = true)
    @NotNull(message = "售价不能为空")
    private BigDecimal price;

    @ApiModelProperty(value = "产品编号", required = true)
    @NotNull(message = "产品编号不能为空")
    private Long productId;

    @ApiModelProperty(value = "店铺编号", required = true)
    @NotNull(message = "店铺编号不能为空")
    private Long branchId;

    @ApiModelProperty(value = "库存", required = true)
    @NotNull(message = "库存不能为空")
    private Integer stock;

}
