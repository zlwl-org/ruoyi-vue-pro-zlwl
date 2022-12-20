package cn.iocoder.yudao.module.infra.dal.dataobject.proxy;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 网络代理 DO
 *
 * @author ruanzh
 */
@TableName("infra_proxy")
@KeySequence("infra_proxy_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProxyDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 名字
     */
    private String name;
    /**
     * 协议
     *
     * 枚举 {@link TODO infra_proxy_protocol 对应的类}
     */
    private String protocol;
    /**
     * 地址
     */
    private String ip;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 密码验证
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private Boolean auth;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态
     *
     * 枚举 {@link TODO common_status 对应的类}
     */
    private Integer status;

}
