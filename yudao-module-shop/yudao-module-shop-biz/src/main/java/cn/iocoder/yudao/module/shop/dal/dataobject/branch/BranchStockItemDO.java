package cn.iocoder.yudao.module.shop.dal.dataobject.branch;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 门店出入库明细 DO
 *
 * @author ruanzh
 */
@TableName("shop_branch_stock_item")
@KeySequence("shop_branch_stock_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchStockItemDO extends BaseDO {

    /**
     * 明细编号
     */
    @TableId
    private Long id;
    /**
     * 库存编号
     */
    private Long stockId;
    /**
     * 出入库类型
     */
    private String type;
    /**
     * 店铺编号
     */
    private Long branchId;
    /**
     * 商品编号
     */
    private Long productId;
    /**
     * 数量
     */
    private Integer amount;

}
