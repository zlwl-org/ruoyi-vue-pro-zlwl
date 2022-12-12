package cn.iocoder.yudao.module.shop.dal.dataobject.order;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.xml.bind.v2.TODO;
import lombok.*;

import java.math.BigDecimal;

/**
 * 门店订单明细 DO
 *
 * @author ruanzh
 */
@TableName("shop_order_item")
@KeySequence("shop_order_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopOrderItemDO extends BaseDO {

    /**
     * 明细编号
     */
    @TableId
    private Long id;
    /**
     * 会员编号
     */
    private Long memberId;
    /**
     * 订单编号
     */
    private Long orderId;
    /**
     * 商品编号
     */
    private Long goodId;
    /**
     * 商品名称
     */
    private String goodName;
    /**
     * 商品售价
     */
    private BigDecimal goodPrice;
    /**
     * 数量
     */
    private Integer amount;
    /**
     * 减免金额
     */
    private BigDecimal discount;
    /**
     * 实际金额
     */
    private BigDecimal realPrice;
    /**
     * 促销活动编号
     */
    private Long promotionId;
    /**
     * 促销活动名称
     */
    private String promotionName;
    /**
     * 类型
     *
     * 枚举 {@link TODO shop_order_item_type 对应的类}
     */
    private String type;

}
