package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 充值订单 Excel VO
 *
 * @author ruanzh
 */
@Data
public class RechargeOrderExcelVO {

    @ExcelProperty("订单编号")
    private Long id;

    @ExcelProperty("会员编号")
    private Integer memberId;

    @ExcelProperty("充值金额")
    private BigDecimal amount;

    @ExcelProperty("充值活动编号")
    private Integer rechargeId;

    @ExcelProperty("充值活动名称")
    private String rechargeName;

    @ExcelProperty(value = "支付方式", converter = DictConvert.class)
    @DictFormat("shop_recharge_pay_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String payType;

    @ExcelProperty(value = "支付状态", converter = DictConvert.class)
    @DictFormat("pay_order_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer payStatus;

    @ExcelProperty("支付时间")
    private Date payTime;

    @ExcelProperty("创建时间")
    private Date createTime;

}
