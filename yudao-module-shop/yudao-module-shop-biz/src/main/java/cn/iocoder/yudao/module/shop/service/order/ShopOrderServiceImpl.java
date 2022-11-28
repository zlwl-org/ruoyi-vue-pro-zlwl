package cn.iocoder.yudao.module.shop.service.order;

import cn.hutool.core.util.NumberUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.shop.convert.order.ShopOrderConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderDO;
import cn.iocoder.yudao.module.shop.dal.mysql.order.ShopOrderMapper;
import cn.iocoder.yudao.module.shop.service.member.ShopMemberAccountService;
import cn.iocoder.yudao.module.shop.service.member.ShopMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.*;

/**
 * 门店订单 Service 实现类
 *
 * @author ruanzh
 */
@Service
@Validated
@Transactional
public class ShopOrderServiceImpl implements ShopOrderService {

    @Resource
    private ShopOrderMapper orderMapper;

    @Resource
    private ShopOrderItemService itemService;

    @Resource
    private ShopMemberService memberService;

    @Resource
    private ShopMemberAccountService memberAccountService;

    @Override
    public Long createOrder(ShopOrderCreateReqVO createReqVO) {

        // 插入
        ShopOrderDO order = ShopOrderConvert.INSTANCE.convert(createReqVO);
        order.setOrderStatus(ShopOrderStatusEnum.UNPAID.getStatus());
        order.setPayStatus(ShopOrderPayStatusEnum.UNPAID.getStatus());
        orderMapper.insert(order);
        // 插入 items
        createReqVO.getItems().forEach(item -> {
            item.setOrderId(order.getId());
            item.setMemberId(order.getMemberId());
            itemService.createOrderItem(item);
        });

        // 结算
        // 是否会员购买，会员购买判断余额
//        if (createReqVO.getMemberId() != null) {
//            // 获取当前余额
//            ShopMemberDO member = memberService.getMember(createReqVO.getMemberId());
//            if (member == null) {
//                throw exception(MEMBER_NOT_EXISTS);
//            }
//            if (member.getBalance().add(member.getGift()).compareTo(createReqVO.getBalancePay()) == -1) {
//                throw exception(MEMBER_BALANCE_NOT_ENOUGH);
//            }
//
//            memberAccountService.shopping(order, member);
//        }
//
//        order.setOrderStatus(ShopOrderStatusEnum.DONE.getStatus());
//        order.setPayStatus(ShopOrderPayStatusEnum.PAID.getStatus());
//        order.setPayTime(new Date());
//
//        orderMapper.updateById(order);

        // 返回
        return order.getId();
    }

    @Override
    public void updateOrder(ShopOrderUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateOrderExists(updateReqVO.getId());
        // 更新
        ShopOrderDO updateObj = ShopOrderConvert.INSTANCE.convert(updateReqVO);
        orderMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrder(Long id) {
        // 校验存在
        this.validateOrderExists(id);
        // 删除
        orderMapper.deleteById(id);
    }

    private void validateOrderExists(Long id) {
        if (orderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }

    @Override
    public ShopOrderDO getOrder(Long id) {
        ShopOrderDO order = orderMapper.selectById(id);
        if (order == null){
            throw exception(ORDER_NOT_EXISTS);
        }
        return order;
    }

    @Override
    public List<ShopOrderDO> getOrderList(Collection<Long> ids) {
        return orderMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ShopOrderDO> getOrderPage(ShopOrderPageReqVO pageReqVO) {
        return orderMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ShopOrderDO> getOrderList(ShopOrderExportReqVO exportReqVO) {
        return orderMapper.selectList(exportReqVO);
    }

    @Override
    public void payOrder(ShopOrderPayVO payVO) {
        ShopOrderDO order = getOrder(payVO.getId());
        if (ShopOrderStatusEnum.DONE.getStatus().equals(order.getOrderStatus())) {
            throw exception(ORDER_IS_DONE);
        }
        // 判断订单是否结清
        BigDecimal paid = NumberUtil.add(order.getBalancePay(), order.getCashPay(), payVO.getAmount());
        int compare = paid.compareTo(order.getPrice());
        if (compare > 0){
            throw exception(ORDER_PAID_LG_PRICE);
        } else if(compare == 0){
            order.setPayStatus(ShopOrderPayStatusEnum.PAID.getStatus());
            order.setOrderStatus(ShopOrderStatusEnum.DONE.getStatus());
        } else {
            order.setPayStatus(ShopOrderPayStatusEnum.PART_PAID.getStatus());
            order.setOrderStatus(ShopOrderStatusEnum.UNPAID.getStatus());

        }

        if ("balance_pay".equals(payVO.getPayType())){
            // 余额支付金额更新
            order.setBalancePay(NumberUtil.add(order.getBalancePay(), payVO.getAmount()));

            ShopMemberDO member = memberService.getMember(order.getMemberId());
            if (member.getBalance().compareTo(payVO.getAmount()) == -1) {
                throw exception(MEMBER_BALANCE_NOT_ENOUGH);
            }
            memberAccountService.shopping(payVO, member);
        } else {
            // 现金支付金额更新
            order.setCashPay(NumberUtil.add(order.getCashPay(), payVO.getAmount()));
        }
        order.setPayTime(new Date());
        order.setPayType(payVO.getPayType());

        orderMapper.updateById(order);
    }

}
