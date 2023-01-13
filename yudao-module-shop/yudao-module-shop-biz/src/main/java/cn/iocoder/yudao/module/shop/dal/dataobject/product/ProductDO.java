package cn.iocoder.yudao.module.shop.dal.dataobject.product;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 产品 DO
 *
 * @author ruanzh
 */
@TableName("shop_product")
@KeySequence("shop_product_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDO extends BaseDO {

    /**
     * 产品编号
     */
    @TableId
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 品牌编号
     */
    private Long brandId;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 分类编号
     */
    private Long categoryId;
    /**
     * 售价
     */
    private BigDecimal price;
    /**
     * 市场价
     */
    private BigDecimal marketPrice;
    /**
     * 成本价
     */
    private BigDecimal costPrice;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 条形码
     */
    private String barCode;
    /**
     * 状态
     *
     * 枚举 {@link TODO common_status 对应的类}
     */
    private Integer status;
    /**
     * 门店编号
     */
    private Long branchId;

}
