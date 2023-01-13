package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 门店 Excel VO
 *
 * @author ZLWL
 */
@Data
public class BranchExcelVO {

    @ExcelProperty("门店编号")
    private Long id;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("电话")
    private String tel;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("disable_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer status;

    @ExcelProperty("创建时间")
    private Date createTime;

}
