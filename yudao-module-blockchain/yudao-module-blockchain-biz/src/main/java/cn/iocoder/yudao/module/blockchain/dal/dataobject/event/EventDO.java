package cn.iocoder.yudao.module.blockchain.dal.dataobject.event;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 事件 DO
 *
 * @author ruanzh
 */
@TableName("blockchain_event")
@KeySequence("blockchain_event_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 主题
     */
    private String topic;
    /**
     * 地址
     */
    private String address;
    /**
     * 网络
     */
    private String net;
    /**
     * 信息
     */
    private String info;

}
