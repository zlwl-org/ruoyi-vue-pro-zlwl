package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 会员 Excel VO
 *
 * @author ZLWL
 */
@Data
public class ShopMemberExcelVO {

    @ExcelProperty("会员编号")
    private Long id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("昵称")
    private String nickname;

    @ExcelProperty("手机号")
    private String mobile;

    @ExcelProperty("销售员")
    private Long salesman;

    @ExcelProperty(value = "客户类型", converter = DictConvert.class)
    @DictFormat("shop_customer_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer type;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("shop_member_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer status;

    @ExcelProperty("积分")
    private BigDecimal point;

    @ExcelProperty("余额")
    private BigDecimal balance;

    @ExcelProperty("成长值")
    private BigDecimal growth;

    @ExcelProperty("店铺编号")
    private Long branchId;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("赠送余额")
    private BigDecimal gift;

}
