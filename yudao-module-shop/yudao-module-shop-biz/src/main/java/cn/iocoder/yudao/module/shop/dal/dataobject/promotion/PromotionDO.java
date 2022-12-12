package cn.iocoder.yudao.module.shop.dal.dataobject.promotion;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 促销活动 DO
 *
 * @author ruanzh
 */
@TableName("shop_promotion")
@KeySequence("shop_promotion_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDO extends BaseDO {

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
     * 促销类型
     *
     * 枚举 {@link TODO shop_promotion_type 对应的类}
     */
    private String promotionType;
    /**
     * 条件
     */
    private BigDecimal condition;
    /**
     * 促销值
     */
    private BigDecimal target;
    /**
     * 产品编号
     */
    private Long productId;
    /**
     * 门店编号
     */
    private Long branchId;
    /**
     * 商品编号
     */
    private Long goodId;
    /**
     * 信息
     */
    private String info;
    /**
     * 状态
     *
     * 枚举 {@link TODO disable_status 对应的类}
     */
    private Integer status;

}
