package cn.iocoder.yudao.module.shop.service.branch;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchStockDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.shop.convert.branch.BranchStockConvert;
import cn.iocoder.yudao.module.shop.dal.mysql.branch.BranchStockMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.*;

/**
 * 门店出入库 Service 实现类
 *
 * @author ruanzh
 */
@Service
@Validated
@Transactional
public class BranchStockServiceImpl implements BranchStockService {

    @Resource
    private BranchStockMapper branchStockMapper;

    @Resource
    private BranchStockItemService itemService;

    @Override
    public Long createBranchStock(BranchStockCreateReqVO createReqVO) {
        // 插入
        BranchStockDO branchStock = BranchStockConvert.INSTANCE.convert(createReqVO);
        branchStockMapper.insert(branchStock);
        // 返回
        List<BranchStockItemCreateReqVO> list = createReqVO.getList();
        list.forEach(item -> {
            item.setBranchId(createReqVO.getBranchId());
            item.setStockId(branchStock.getId());
            itemService.createBranchStockItem(item);
        });
//        itemService.createBranchStockItems(list);
        return branchStock.getId();
    }

    @Override
    public void updateBranchStock(BranchStockUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateBranchStockExists(updateReqVO.getId());
        // 更新
        BranchStockDO updateObj = BranchStockConvert.INSTANCE.convert(updateReqVO);
        branchStockMapper.updateById(updateObj);
    }

    @Override
    public void deleteBranchStock(Long id) {
        // 校验存在
        this.validateBranchStockExists(id);
        // 删除
        branchStockMapper.deleteById(id);
    }

    private void validateBranchStockExists(Long id) {
        if (branchStockMapper.selectById(id) == null) {
            throw exception(BRANCH_STOCK_NOT_EXISTS);
        }
    }

    @Override
    public BranchStockDO getBranchStock(Long id) {
        return branchStockMapper.selectById(id);
    }

    @Override
    public List<BranchStockDO> getBranchStockList(Collection<Long> ids) {
        return branchStockMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BranchStockDO> getBranchStockPage(BranchStockPageReqVO pageReqVO) {
        return branchStockMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BranchStockDO> getBranchStockList(BranchStockExportReqVO exportReqVO) {
        return branchStockMapper.selectList(exportReqVO);
    }

}
