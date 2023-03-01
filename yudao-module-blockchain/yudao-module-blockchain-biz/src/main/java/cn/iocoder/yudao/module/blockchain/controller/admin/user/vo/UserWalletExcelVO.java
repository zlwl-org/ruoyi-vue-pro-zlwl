package cn.iocoder.yudao.module.blockchain.controller.admin.user.vo;

import lombok.*;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;


import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 用户钱包 Excel VO
 *
 * @author ruanzh
 */
@Data
public class UserWalletExcelVO {

    @ExcelProperty("钱包编号")
    private Long id;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty(value = "网络", converter = DictConvert.class)
    @DictFormat("blockchain_net_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String net;

    @ExcelProperty("代币")
    private String symbol;

    @ExcelProperty("余额")
    private BigDecimal balance;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
