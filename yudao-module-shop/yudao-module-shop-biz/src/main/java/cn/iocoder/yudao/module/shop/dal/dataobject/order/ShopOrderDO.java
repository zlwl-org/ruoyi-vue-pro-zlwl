package cn.iocoder.yudao.module.shop.dal.dataobject.order;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 门店订单 DO
 *
 * @author ruanzh
 */
@TableName("shop_order")
@KeySequence("shop_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopOrderDO extends BaseDO {

    /**
     * 订单编号
     */
    @TableId
    private Long id;
    /**
     * 会员编号
     */
    private Long memberId;
    /**
     * 订单类型
     *
     * 枚举 {@link TODO shop_order_type 对应的类}
     */
    private String orderType;
    /**
     * 订单交易号
     */
    private String orderNo;
    /**
     * 订单状态
     *
     * 枚举 {@link TODO shop_order_status 对应的类}
     */
    private String orderStatus;
    /**
     * 付款方式
     *
     * 枚举 {@link TODO shop_pay_status 对应的类}
     */
    private String payType;
    /**
     * 支付状态
     *
     * 枚举 {@link TODO shop_order_pay_status 对应的类}
     */
    private String payStatus;
    /**
     * 付款时间
     */
    private Date payTime;
    /**
     * 收银员
     */
    private Long cashier;
    /**
     * 商品总价
     */
    private BigDecimal price;
    /**
     * 店铺优惠
     */
    private BigDecimal branchDiscount;
    /**
     * 订单减免
     */
    private BigDecimal orderDiscount;
    /**
     * 优惠券
     */
    private String coupon;
    /**
     * 积分抵扣
     */
    private String point;
    /**
     * 余额实付金额
     */
    private BigDecimal balancePay;
    /**
     * 现金实付金额
     */
    private BigDecimal cashPay;
    /**
     * 店铺编号
     */
    private Long branchId;

}
