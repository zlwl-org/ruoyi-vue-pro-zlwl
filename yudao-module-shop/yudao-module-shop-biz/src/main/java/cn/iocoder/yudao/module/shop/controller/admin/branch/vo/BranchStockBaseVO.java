package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 门店出入库 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class BranchStockBaseVO {

    @ApiModelProperty(value = "店铺编号", required = true)
    @NotNull(message = "店铺编号不能为空")
    private Long branchId;

}
