package cn.iocoder.yudao.module.shop.dal.dataobject.order;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

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

}
