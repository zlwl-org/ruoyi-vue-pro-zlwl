package cn.iocoder.yudao.module.shop.dal.dataobject.brand;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商品品牌 DO
 *
 * @author ZLWL
 */
@TableName("shop_brand")
@KeySequence("shop_brand_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandDO extends BaseDO {

    /**
     * 品牌编号
     */
    @TableId
    private Long id;
    /**
     * 品牌名称
     */
    private String name;
    /**
     * 品牌图片
     */
    private String picUrl;
    /**
     * 品牌排序
     */
    private Integer sort;
    /**
     * 品牌描述
     */
    private String description;
    /**
     * 状态
     */
    private Integer status;

}
