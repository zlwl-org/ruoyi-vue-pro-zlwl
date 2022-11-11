package cn.iocoder.yudao.module.shop.dal.dataobject.recharge;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 充值活动 DO
 *
 * @author ZLWL
 */
@TableName("shop_recharge")
@KeySequence("shop_recharge_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RechargeDO extends BaseDO {

    /**
     * 活动编号
     */
    @TableId
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 充值金额
     */
    private BigDecimal price;
    /**
     * 赠送金额
     */
    private BigDecimal gift;
    /**
     * 赠送积分
     */
    private BigDecimal point;
    /**
     * 赠送成长值
     */
    private BigDecimal growth;
    /**
     * 赠送优惠券
     */
    private String coupon;
    /**
     * 状态
     *
     * 枚举 {@link TODO disable_status 对应的类}
     */
    private Integer status;

}
