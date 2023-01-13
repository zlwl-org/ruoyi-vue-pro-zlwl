package cn.iocoder.yudao.module.shop.controller.admin.category.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@ApiModel(value = "管理后台 - 商品分类 Excel 导出 Request VO", description = "参数和 CategoryPageReqVO 是一致的")
@Data
public class CategoryExportReqVO {

    @ApiModelProperty(value = "父分类编号")
    private Long parentId;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "开启状态")
    private Integer status;

}
