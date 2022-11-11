package cn.iocoder.yudao.module.shop.controller.admin.brand.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 商品品牌 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class BrandBaseVO {

    @ApiModelProperty(value = "品牌名称", required = true)
    @NotNull(message = "品牌名称不能为空")
    private String name;

    @ApiModelProperty(value = "品牌图片")
    private String picUrl;

    @ApiModelProperty(value = "品牌排序")
    private Integer sort;

}
