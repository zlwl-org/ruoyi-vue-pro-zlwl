package cn.iocoder.yudao.module.shop.controller.admin.category.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 商品分类 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CategoryBaseVO {

    @Schema(description = "父分类编号")
    private Long parentId;

    @Schema(description = "分类名称", required = true)
    @NotNull(message = "分类名称不能为空")
    private String name;

    @Schema(description = "分类图片")
    private String picUrl;

    @Schema(description = "分类排序")
    private Integer sort;

    @Schema(description = "分类描述")
    private String description;

    @Schema(description = "开启状态", required = true)
    @NotNull(message = "开启状态不能为空")
    private Integer status;

}
