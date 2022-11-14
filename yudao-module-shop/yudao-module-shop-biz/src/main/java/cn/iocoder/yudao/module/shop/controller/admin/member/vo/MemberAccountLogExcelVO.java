package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 会员账户流水 Excel VO
 *
 * @author ZLWL
 */
@Data
public class MemberAccountLogExcelVO {

    @ExcelProperty("流水编号")
    private Long id;

    @ExcelProperty("会员编号")
    private Long memberId;

    @ExcelProperty("发生方式")
    private String action;

    @ExcelProperty("关联表编号")
    private Long relatedId;

    @ExcelProperty("充值余额变动")
    private BigDecimal balance;

    @ExcelProperty("赠送余额变动")
    private BigDecimal gift;

    @ExcelProperty("积分变动")
    private BigDecimal point;

    @ExcelProperty("成长值变动")
    private BigDecimal growth;

    @ExcelProperty("信息")
    private String info;

    @ExcelProperty("创建时间")
    private Date createTime;

}
