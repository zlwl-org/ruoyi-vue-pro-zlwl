package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 门店 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class BranchBaseVO {

    @ApiModelProperty(value = "名称", required = true, example = "总店")
    @NotNull(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "电话")
    private String tel;

    @ApiModelProperty(value = "状态", required = true, example = "0")
    @NotNull(message = "状态不能为空")
    private Integer status;

}
