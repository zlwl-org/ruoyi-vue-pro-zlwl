package cn.iocoder.yudao.module.shop.dal.dataobject.branch;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 门店出入库 DO
 *
 * @author ruanzh
 */
@TableName("shop_branch_stock")
@KeySequence("shop_branch_stock_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchStockDO extends BaseDO {

    /**
     * 库存编号
     */
    @TableId
    private Long id;
    /**
     * 店铺编号
     */
    private Long branchId;

}
