package cn.iocoder.yudao.module.shop.controller.admin.product.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 产品 Excel VO
 *
 * @author ruanzh
 */
@Data
public class ProductExcelVO {

    @ExcelProperty("产品编号")
    private Long id;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("品牌名称")
    private String brandName;

    @ExcelProperty("售价")
    private BigDecimal price;

    @ExcelProperty("市场价")
    private BigDecimal marketPrice;

    @ExcelProperty("成本价")
    private BigDecimal costPrice;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer status;

    @ExcelProperty("门店编号")
    private Long branchId;

    @ExcelProperty("创建时间")
    private Date createTime;

}
