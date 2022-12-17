package cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 网络 Excel VO
 *
 * @author ruanzh
 */
@Data
public class NetExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("中文名称")
    private Integer nameZh;

    @ExcelProperty("原生代币")
    private String symbol;

    @ExcelProperty("浏览器")
    private String explorer;

    @ExcelProperty("默认节点")
    private String publicRpc;

    @ExcelProperty("私密节点")
    private String privateRpc;

    @ExcelProperty("网络类型")
    private String type;

    @ExcelProperty("链ID")
    private Integer chainId;

}
