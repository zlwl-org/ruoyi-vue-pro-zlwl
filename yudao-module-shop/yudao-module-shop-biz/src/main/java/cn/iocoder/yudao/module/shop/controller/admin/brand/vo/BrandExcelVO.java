package cn.iocoder.yudao.module.shop.controller.admin.brand.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 商品品牌 Excel VO
 *
 * @author ZLWL
 */
@Data
public class BrandExcelVO {

    @ExcelProperty("品牌编号")
    private Long id;

    @ExcelProperty("品牌名称")
    private String name;

    @ExcelProperty("品牌图片")
    private String picUrl;

    @ExcelProperty("品牌排序")
    private Integer sort;

    @ExcelProperty("创建时间")
    private Date createTime;

}
