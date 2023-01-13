package cn.iocoder.yudao.module.shop.dal.dataobject.member;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * 会员账户流水 DO
 *
 * @author ZLWL
 */
@TableName("shop_member_account_log")
@KeySequence("shop_member_account_log_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberAccountLogDO extends BaseDO {

    /**
     * 流水编号
     */
    @TableId
    private Long id;
    /**
     * 会员编号
     */
    private Long memberId;
    /**
     * 发生方式
     */
    private String action;
    /**
     * 关联表编号
     */
    private Long relatedId;
    /**
     * 充值余额变动
     */
    private BigDecimal balance;
    /**
     * 赠送余额变动
     */
    private BigDecimal gift;
    /**
     * 积分变动
     */
    private BigDecimal point;
    /**
     * 成长值变动
     */
    private BigDecimal growth;
    /**
     * 信息
     */
    private String info;

}
