package cn.iocoder.yudao.module.shop.controller.admin.category.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 商品分类分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryPageReqVO extends PageParam {

    @Schema(description = "父分类编号")
    private Long parentId;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "开启状态")
    private Integer status;

}
