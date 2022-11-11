package cn.iocoder.yudao.module.shop.dal.dataobject.recharge;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 会员充值套餐 DO
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
     * 充值套餐编号
     */
    @TableId
    private Long id;
    /**
     * 套餐名称
     */
    private String name;
    /**
     * 面值
     */
    private BigDecimal faceValue;
    /**
     * 售价
     */
    private BigDecimal price;
    /**
     * 积分
     */
    private BigDecimal point;
    /**
     * 成长值
     */
    private BigDecimal growth;
    /**
     * 优惠券
     */
    private String couponId;
    /**
     * 不限数量
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private Boolean unlimited;
    /**
     * 发放数量
     */
    private Integer saleNum;
    /**
     * 状态
     *
     * 枚举 {@link TODO disable_status 对应的类}
     */
    private Integer status;

}
