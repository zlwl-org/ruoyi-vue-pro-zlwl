package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import lombok.*;

import java.util.*;


import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 门店出入库明细 Excel VO
 *
 * @author ruanzh
 */
@Data
public class BranchStockItemExcelVO {

    @ExcelProperty("明细编号")
    private Long id;

    @ExcelProperty("库存编号")
    private Long stockId;

    @ExcelProperty("出入库类型")
    private String type;

    @ExcelProperty("店铺编号")
    private Long branchId;

    @ExcelProperty("商品编号")
    private Long productId;

    @ExcelProperty("数量")
    private Integer amount;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("产品名称")
    private String productName;

}
