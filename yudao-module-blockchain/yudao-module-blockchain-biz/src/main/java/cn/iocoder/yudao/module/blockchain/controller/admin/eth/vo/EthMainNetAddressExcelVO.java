package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 以太坊主网地址 Excel VO
 *
 * @author ruanzh
 */
@Data
public class EthMainNetAddressExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("余额")
    private BigDecimal balance;

    @ExcelProperty("标签")
    private String tags;

    @ExcelProperty("更新时间")
    private Date updateTime;

}
