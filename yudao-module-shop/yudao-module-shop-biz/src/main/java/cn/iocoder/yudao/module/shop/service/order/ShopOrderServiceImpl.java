package cn.iocoder.yudao.module.shop.service.order;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderCreateReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderPageReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderUpdateReqVO;
import cn.iocoder.yudao.module.shop.convert.order.ShopOrderConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderDO;
import cn.iocoder.yudao.module.shop.dal.mysql.order.ShopOrderMapper;
import cn.iocoder.yudao.module.shop.service.member.ShopMemberAccountService;
import cn.iocoder.yudao.module.shop.service.member.ShopMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.ORDER_NOT_EXISTS;

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
        return orderMapper.selectById(id);
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

}
