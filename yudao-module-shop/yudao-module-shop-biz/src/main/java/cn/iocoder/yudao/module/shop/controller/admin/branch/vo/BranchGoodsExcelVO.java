package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 门店商品 Excel VO
 *
 * @author ruanzh
 */
@Data
public class BranchGoodsExcelVO {

    @ExcelProperty("商品编号")
    private Long id;

    @ExcelProperty("商品名称")
    private String name;

    @ExcelProperty("售价")
    private BigDecimal price;

    @ExcelProperty("产品编号")
    private Long productId;

    @ExcelProperty("店铺编号")
    private Long branchId;

    @ExcelProperty("库存")
    private Integer stock;

    @ExcelProperty("创建时间")
    private Date createTime;

}
