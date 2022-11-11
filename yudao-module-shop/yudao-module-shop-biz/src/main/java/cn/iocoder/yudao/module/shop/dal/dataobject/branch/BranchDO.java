package cn.iocoder.yudao.module.shop.dal.dataobject.branch;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 门店 DO
 *
 * @author ZLWL
 */
@TableName("shop_branch")
@KeySequence("shop_branch_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDO extends BaseDO {

    /**
     * 门店编号
     */
    @TableId
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 地址
     */
    private String address;
    /**
     * 电话
     */
    private String tel;
    /**
     * 状态
     *
     * 枚举 {@link TODO disable_status 对应的类}
     */
    private Integer status;

}
