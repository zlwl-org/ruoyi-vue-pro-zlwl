package cn.iocoder.yudao.module.shop.dal.dataobject.recharge;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 充值订单 DO
 *
 * @author ZLWL
 */
@TableName("shop_recharge_order")
@KeySequence("shop_recharge_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RechargeOrderDO extends BaseDO {

    /**
     * 充值订单编号
     */
    @TableId
    private Long id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 订单流水号
     */
    private String outTradeNo;
    /**
     * 充值金额
     */
    private BigDecimal rechargeAmount;
    /**
     * 套餐编号
     */
    private Integer rechargeId;
    /**
     * 套餐名称
     */
    private String rechargeName;
    /**
     * 套餐面值
     */
    private BigDecimal rechargeFaceValue;
    /**
     * 套餐售价
     */
    private BigDecimal rechargePrice;
    /**
     * 套餐赠送积分
     */
    private BigDecimal rechargePoint;
    /**
     * 套餐赠送成长值
     */
    private BigDecimal rechargeGrowth;
    /**
     * 套餐赠送优惠券
     */
    private String rechargeCoupon;
    /**
     * 支付方式
     */
    private String payType;
    /**
     * 支付状态
     */
    private Integer status;
    /**
     * 支付时间
     */
    private Integer payTime;
    /**
     * 会员编号
     */
    private Integer memberId;
    /**
     * 订单来源
     */
    private String orderFrom;
    /**
     * 订单来源名称
     */
    private String orderFromName;

}
