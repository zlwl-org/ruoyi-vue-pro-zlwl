package cn.iocoder.yudao.module.shop.service.branch;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchStockItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.shop.convert.branch.BranchStockItemConvert;
import cn.iocoder.yudao.module.shop.dal.mysql.branch.BranchStockItemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.*;

/**
 * 门店出入库明细 Service 实现类
 *
 * @author ruanzh
 */
@Service
@Validated
public class BranchStockItemServiceImpl implements BranchStockItemService {

    @Resource
    private BranchStockItemMapper branchStockItemMapper;

    @Override
    public Long createBranchStockItem(BranchStockItemCreateReqVO createReqVO) {
        // 插入
        BranchStockItemDO branchStockItem = BranchStockItemConvert.INSTANCE.convert(createReqVO);
        branchStockItemMapper.insert(branchStockItem);
        // 返回
        return branchStockItem.getId();
    }

    @Override
    public void updateBranchStockItem(BranchStockItemUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateBranchStockItemExists(updateReqVO.getId());
        // 更新
        BranchStockItemDO updateObj = BranchStockItemConvert.INSTANCE.convert(updateReqVO);
        branchStockItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteBranchStockItem(Long id) {
        // 校验存在
        this.validateBranchStockItemExists(id);
        // 删除
        branchStockItemMapper.deleteById(id);
    }

    private void validateBranchStockItemExists(Long id) {
        if (branchStockItemMapper.selectById(id) == null) {
            throw exception(BRANCH_STOCK_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public BranchStockItemDO getBranchStockItem(Long id) {
        return branchStockItemMapper.selectById(id);
    }

    @Override
    public List<BranchStockItemDO> getBranchStockItemList(Collection<Long> ids) {
        return branchStockItemMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BranchStockItemDO> getBranchStockItemPage(BranchStockItemPageReqVO pageReqVO) {
        return branchStockItemMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BranchStockItemDO> getBranchStockItemList(BranchStockItemExportReqVO exportReqVO) {
        return branchStockItemMapper.selectList(exportReqVO);
    }

    @Override
    public void createBranchStockItems(List<BranchStockItemCreateReqVO> list) {

    }

}
