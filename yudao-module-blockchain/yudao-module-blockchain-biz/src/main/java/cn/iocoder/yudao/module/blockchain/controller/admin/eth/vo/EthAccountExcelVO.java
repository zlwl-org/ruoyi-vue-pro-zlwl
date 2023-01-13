package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 以太坊账户 Excel VO
 *
 * @author ruanzh
 */
@Data
public class EthAccountExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("助记词")
    private String mnemonic;

    @ExcelProperty("私钥")
    private String privateKey;

    @ExcelProperty("归属")
    private Boolean owned;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("网络")
    private String net;

}
