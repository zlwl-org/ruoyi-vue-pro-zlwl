package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 充值活动 Excel VO
 *
 * @author ZLWL
 */
@Data
public class RechargeExcelVO {

    @ExcelProperty("活动编号")
    private Long id;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("充值金额")
    private BigDecimal price;

    @ExcelProperty("赠送金额")
    private BigDecimal gift;

    @ExcelProperty("赠送积分")
    private BigDecimal point;

    @ExcelProperty("赠送成长值")
    private BigDecimal growth;

    @ExcelProperty("赠送优惠券")
    private String coupon;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("disable_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer status;

    @ExcelProperty("创建时间")
    private Date createTime;

}
