package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import cn.iocoder.yudao.module.shop.controller.admin.member.vo.ShopMemberRespVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ApiModel("管理后台 - 门店订单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopOrderRespVO extends ShopOrderBaseVO {

    @ApiModelProperty(value = "订单编号", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;


    private ShopMemberRespVO member;

    private List<ShopOrderItemRespVO> items;

}
