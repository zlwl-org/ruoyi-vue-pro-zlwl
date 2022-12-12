package cn.iocoder.yudao.module.shop.controller.admin.promotion.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 促销活动 Excel VO
 *
 * @author ruanzh
 */
@Data
public class PromotionExcelVO {

    @ExcelProperty("活动编号")
    private Long id;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty(value = "促销类型", converter = DictConvert.class)
    @DictFormat("shop_promotion_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String promotionType;

    @ExcelProperty("条件")
    private BigDecimal condition;

    @ExcelProperty("促销值")
    private BigDecimal target;

    @ExcelProperty("产品编号")
    private Long productId;

    @ExcelProperty("门店编号")
    private Long branchId;

    @ExcelProperty("商品编号")
    private Long goodId;

    @ExcelProperty("信息")
    private String info;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("disable_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer status;

    @ExcelProperty("创建时间")
    private Date createTime;

}
