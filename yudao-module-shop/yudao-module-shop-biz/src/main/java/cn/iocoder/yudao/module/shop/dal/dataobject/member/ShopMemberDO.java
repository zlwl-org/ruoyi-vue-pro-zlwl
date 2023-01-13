package cn.iocoder.yudao.module.shop.dal.dataobject.member;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 会员 DO
 *
 * @author ZLWL
 */
@TableName("shop_member")
@KeySequence("shop_member_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopMemberDO extends BaseDO {

    /**
     * 会员编号
     */
    @TableId
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 销售员
     */
    private Long salesman;
    /**
     * 客户类型
     *
     * 枚举 {@link TODO shop_customer_type 对应的类}
     */
    private Integer type;
    /**
     * 状态
     *
     * 枚举 {@link TODO shop_member_status 对应的类}
     */
    private Integer status;
    /**
     * 积分
     */
    private BigDecimal point;
    /**
     * 余额
     */
    private BigDecimal balance;
    /**
     * 成长值
     */
    private BigDecimal growth;
    /**
     * 店铺编号
     */
    private Long branchId;
    /**
     * 赠送余额
     */
    private BigDecimal gift;

}
