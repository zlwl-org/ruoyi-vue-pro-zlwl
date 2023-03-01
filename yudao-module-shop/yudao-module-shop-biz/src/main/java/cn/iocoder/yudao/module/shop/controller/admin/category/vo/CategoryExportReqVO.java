package cn.iocoder.yudao.module.shop.controller.admin.category.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "管理后台 - 商品分类 Excel 导出 Request VO", description = "参数和 CategoryPageReqVO 是一致的")
@Data
public class CategoryExportReqVO {

    @Schema(description = "父分类编号")
    private Long parentId;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "开启状态")
    private Integer status;

}
