package cn.iocoder.yudao.module.blockchain.controller.admin.event.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 事件 Excel VO
 *
 * @author ruanzh
 */
@Data
public class EventExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("主题")
    private String topic;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("网络")
    private String net;

    @ExcelProperty("信息")
    private String info;

    @ExcelProperty("创建时间")
    private Date createTime;

}
