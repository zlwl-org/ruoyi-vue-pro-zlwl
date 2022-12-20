package cn.iocoder.yudao.module.infra.controller.admin.proxy.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 网络代理 Excel VO
 *
 * @author ruanzh
 */
@Data
public class ProxyExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("名字")
    private String name;

    @ExcelProperty(value = "协议", converter = DictConvert.class)
    @DictFormat("infra_proxy_protocol") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String protocol;

    @ExcelProperty("地址")
    private String ip;

    @ExcelProperty("端口")
    private Integer port;

    @ExcelProperty(value = "密码验证", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Boolean auth;

    @ExcelProperty("用户名")
    private String username;

    @ExcelProperty("密码")
    private String password;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer status;

    @ExcelProperty("创建时间")
    private Date createTime;

}
