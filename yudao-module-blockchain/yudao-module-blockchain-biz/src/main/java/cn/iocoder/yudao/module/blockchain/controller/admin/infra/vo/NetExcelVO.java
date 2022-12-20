package cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


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
    private String nameZh;

    @ExcelProperty("原生代币")
    private String symbol;

    @ExcelProperty("浏览器")
    private String explorer;

    @ExcelProperty("默认节点")
    private String publicRpc;

    @ExcelProperty("私密节点")
    private String privateRpc;

    @ExcelProperty(value = "网络类型", converter = DictConvert.class)
    @DictFormat("blockchain_net_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String type;

    @ExcelProperty("链ID")
    private Integer chainId;

}
