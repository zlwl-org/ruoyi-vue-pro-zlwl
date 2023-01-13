package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* 门店 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class BranchSimpleRespVO {

    @ApiModelProperty(value = "门店编号", required = true)
    private Long id;

    @ApiModelProperty(value = "名称", required = true)
    private String name;

}
