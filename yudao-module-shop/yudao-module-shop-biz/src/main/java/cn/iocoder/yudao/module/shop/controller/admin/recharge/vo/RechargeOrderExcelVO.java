package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 充值订单 Excel VO
 *
 * @author ZLWL
 */
@Data
public class RechargeOrderExcelVO {

    @ExcelProperty("充值订单编号")
    private Long id;

    @ExcelProperty("订单编号")
    private String orderNo;

    @ExcelProperty("订单流水号")
    private String outTradeNo;

    @ExcelProperty("充值金额")
    private BigDecimal rechargeAmount;

    @ExcelProperty("套餐编号")
    private Integer rechargeId;

    @ExcelProperty("支付方式")
    private String payType;

    @ExcelProperty("支付状态")
    private Integer status;

    @ExcelProperty("支付时间")
    private Integer payTime;

    @ExcelProperty("会员编号")
    private Integer memberId;

    @ExcelProperty("订单来源")
    private String orderFrom;

    @ExcelProperty("订单来源名称")
    private String orderFromName;

    @ExcelProperty("创建时间")
    private Date createTime;

}
