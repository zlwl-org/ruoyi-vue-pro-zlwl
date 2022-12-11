package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 门店订单 Excel VO
 *
 * @author ruanzh
 */
@Data
public class ShopOrderExcelVO {

    @ExcelProperty("订单编号")
    private Long id;

    @ExcelProperty("会员编号")
    private Long memberId;

    @ExcelProperty(value = "订单类型", converter = DictConvert.class)
    @DictFormat("shop_order_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String orderType;

    @ExcelProperty("订单交易号")
    private String orderNo;

    @ExcelProperty(value = "订单状态", converter = DictConvert.class)
    @DictFormat("shop_order_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String orderStatus;

    @ExcelProperty(value = "付款方式", converter = DictConvert.class)
    @DictFormat("shop_pay_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String payType;

    @ExcelProperty(value = "支付状态", converter = DictConvert.class)
    @DictFormat("shop_order_pay_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String payStatus;

    @ExcelProperty("付款时间")
    private Date payTime;

    @ExcelProperty("收银员")
    private Long cashier;


    @ExcelProperty("订单金额")
    private BigDecimal orderPrice;

    @ExcelProperty("实付金额")
    private BigDecimal price;

    @ExcelProperty("余额实付金额")
    private BigDecimal balancePay;

    @ExcelProperty("现金实付金额")
    private BigDecimal cashPay;

    @ExcelProperty("店铺编号")
    private Long branchId;

    @ExcelProperty("创建时间")
    private Date createTime;


}
