package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 门店 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BranchBaseVO {

    @Schema(description = "名称", required = true, example = "总店")
    @NotNull(message = "名称不能为空")
    private String name;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "电话")
    private String tel;

    @Schema(description = "状态", required = true, example = "0")
    @NotNull(message = "状态不能为空")
    private Integer status;

}
