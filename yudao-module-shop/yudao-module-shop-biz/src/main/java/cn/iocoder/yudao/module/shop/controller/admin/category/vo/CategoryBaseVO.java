package cn.iocoder.yudao.module.shop.controller.admin.category.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 商品分类 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class CategoryBaseVO {

    @ApiModelProperty(value = "父分类编号")
    private Long parentId;

    @ApiModelProperty(value = "分类名称", required = true)
    @NotNull(message = "分类名称不能为空")
    private String name;

    @ApiModelProperty(value = "分类图片")
    private String picUrl;

    @ApiModelProperty(value = "分类排序")
    private Integer sort;

    @ApiModelProperty(value = "分类描述")
    private String description;

    @ApiModelProperty(value = "开启状态", required = true)
    @NotNull(message = "开启状态不能为空")
    private Integer status;

}
