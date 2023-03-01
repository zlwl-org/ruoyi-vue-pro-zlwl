package cn.iocoder.yudao.module.shop.controller.admin.brand.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 商品品牌 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BrandBaseVO {

    @Schema(description = "品牌名称", required = true)
    @NotNull(message = "品牌名称不能为空")
    private String name;

    @Schema(description = "品牌图片")
    private String picUrl;

    @Schema(description = "品牌排序")
    private Integer sort;

}
