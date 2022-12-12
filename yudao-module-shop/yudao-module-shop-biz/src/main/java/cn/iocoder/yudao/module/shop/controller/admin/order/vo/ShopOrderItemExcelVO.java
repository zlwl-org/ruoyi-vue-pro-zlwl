package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


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

    @ExcelProperty("减免金额")
    private BigDecimal discount;

    @ExcelProperty("实际金额")
    private BigDecimal realPrice;

    @ExcelProperty("促销活动编号")
    private Long promotionId;

    @ExcelProperty("促销活动名称")
    private String promotionName;

    @ExcelProperty(value = "类型", converter = DictConvert.class)
    @DictFormat("shop_order_item_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String type;

}
