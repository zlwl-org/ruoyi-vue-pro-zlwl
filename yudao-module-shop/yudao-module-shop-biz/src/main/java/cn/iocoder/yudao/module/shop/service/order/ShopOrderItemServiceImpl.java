package cn.iocoder.yudao.module.shop.service.order;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderItemCreateReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderItemExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderItemPageReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderItemUpdateReqVO;
import cn.iocoder.yudao.module.shop.convert.order.ShopOrderItemConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderItemDO;
import cn.iocoder.yudao.module.shop.dal.mysql.order.ShopOrderItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.ORDER_ITEM_NOT_EXISTS;

/**
 * 门店订单明细 Service 实现类
 *
 * @author ruanzh
 */
@Service
@Validated
public class ShopOrderItemServiceImpl implements ShopOrderItemService {

    @Resource
    private ShopOrderItemMapper orderItemMapper;

    @Override
    public Long createOrderItem(ShopOrderItemCreateReqVO createReqVO) {
        // 插入
        ShopOrderItemDO orderItem = ShopOrderItemConvert.INSTANCE.convert(createReqVO);
        orderItemMapper.insert(orderItem);
        // 返回
        return orderItem.getId();
    }

    @Override
    public void updateOrderItem(ShopOrderItemUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateOrderItemExists(updateReqVO.getId());
        // 更新
        ShopOrderItemDO updateObj = ShopOrderItemConvert.INSTANCE.convert(updateReqVO);
        orderItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderItem(Long id) {
        // 校验存在
        this.validateOrderItemExists(id);
        // 删除
        orderItemMapper.deleteById(id);
    }

    private void validateOrderItemExists(Long id) {
        if (orderItemMapper.selectById(id) == null) {
            throw exception(ORDER_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public ShopOrderItemDO getOrderItem(Long id) {
        return orderItemMapper.selectById(id);
    }

    @Override
    public List<ShopOrderItemDO> getOrderItemList(Collection<Long> ids) {
        return orderItemMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ShopOrderItemDO> getOrderItemPage(ShopOrderItemPageReqVO pageReqVO) {
        return orderItemMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ShopOrderItemDO> getOrderItemList(ShopOrderItemExportReqVO exportReqVO) {
        return orderItemMapper.selectList(exportReqVO);
    }

    @Override
    public List<ShopOrderItemDO> getOrderItemList(Long orderId) {
        return orderItemMapper.selectListByOrderId(orderId);
    }

}
