package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 门店订单明细 Excel VO
 *
 * @author ruanzh
 */
@Data
public class ShopOrderItemExcelVO {

    @ExcelProperty("明细编号")
    private Long id;

    @ExcelProperty("会员编号")
    private Long memberId;

    @ExcelProperty("订单编号")
    private Long orderId;

    @ExcelProperty("商品编号")
    private Long goodId;

    @ExcelProperty("商品名称")
    private String goodName;

    @ExcelProperty("商品售价")
    private BigDecimal goodPrice;

    @ExcelProperty("数量")
    private Integer amount;

    @ExcelProperty("创建时间")
    private Date createTime;

}
