package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 会员充值套餐 Excel VO
 *
 * @author ZLWL
 */
@Data
public class RechargeExcelVO {

    @ExcelProperty("充值套餐编号")
    private Long id;

    @ExcelProperty("套餐名称")
    private String name;

    @ExcelProperty("面值")
    private BigDecimal faceValue;

    @ExcelProperty("售价")
    private BigDecimal price;

    @ExcelProperty("积分")
    private BigDecimal point;

    @ExcelProperty("成长值")
    private BigDecimal growth;

    @ExcelProperty("优惠券")
    private String couponId;

    @ExcelProperty(value = "不限数量", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Boolean unlimited;

    @ExcelProperty("发放数量")
    private Integer saleNum;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("disable_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer status;

    @ExcelProperty("创建时间")
    private Date createTime;

}
