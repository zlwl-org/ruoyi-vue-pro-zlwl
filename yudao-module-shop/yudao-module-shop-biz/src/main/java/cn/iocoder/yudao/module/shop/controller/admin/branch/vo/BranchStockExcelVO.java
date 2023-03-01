package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import lombok.*;

import java.util.*;


import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 门店出入库 Excel VO
 *
 * @author ruanzh
 */
@Data
public class BranchStockExcelVO {

    @ExcelProperty("库存编号")
    private Long id;

    @ExcelProperty("店铺编号")
    private Long branchId;

    @ExcelProperty("创建时间")
    private Date createTime;

}
