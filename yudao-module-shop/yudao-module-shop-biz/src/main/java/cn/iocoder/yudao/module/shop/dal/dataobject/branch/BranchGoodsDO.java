package cn.iocoder.yudao.module.shop.dal.dataobject.branch;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 门店商品 DO
 *
 * @author ruanzh
 */
@TableName("shop_branch_goods")
@KeySequence("shop_branch_goods_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchGoodsDO extends BaseDO {

    /**
     * 商品编号
     */
    @TableId
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 售价
     */
    private BigDecimal price;
    /**
     * 产品编号
     */
    private Long productId;
    /**
     * 店铺编号
     */
    private Long branchId;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 品牌编号
     */
    private Long brandId;

}
