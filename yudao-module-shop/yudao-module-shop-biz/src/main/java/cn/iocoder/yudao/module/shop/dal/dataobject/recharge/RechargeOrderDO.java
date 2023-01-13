package cn.iocoder.yudao.module.shop.dal.dataobject.recharge;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.xml.bind.v2.TODO;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值订单 DO
 *
 * @author ruanzh
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
     * 订单编号
     */
    @TableId
    private Long id;
    /**
     * 会员编号
     */
    private Long memberId;
    /**
     * 充值金额
     */
    private BigDecimal amount;
    /**
     * 充值活动编号
     */
    private Long rechargeId;
    /**
     * 充值活动名称
     */
    private String rechargeName;
    /**
     * 支付方式
     *
     * 枚举 {@link TODO shop_recharge_pay_type 对应的类}
     */
    private String payType;
    /**
     * 支付状态
     *
     * 枚举 {@link TODO pay_order_status 对应的类}
     */
    private Integer payStatus;
    /**
     * 支付时间
     */
    private Date payTime;

}
