package cn.iocoder.yudao.module.shop.service.branch;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchGoodsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.shop.convert.branch.BranchGoodsConvert;
import cn.iocoder.yudao.module.shop.dal.mysql.branch.BranchGoodsMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.*;

/**
 * 门店商品 Service 实现类
 *
 * @author ruanzh
 */
@Service
@Validated
public class BranchGoodsServiceImpl implements BranchGoodsService {

    @Resource
    private BranchGoodsMapper branchGoodsMapper;

    @Override
    public Long createBranchGoods(BranchGoodsCreateReqVO createReqVO) {
        // 插入
        BranchGoodsDO branchGoods = BranchGoodsConvert.INSTANCE.convert(createReqVO);
        branchGoodsMapper.insert(branchGoods);
        // 返回
        return branchGoods.getId();
    }

    @Override
    public void updateBranchGoods(BranchGoodsUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateBranchGoodsExists(updateReqVO.getId());
        // 更新
        BranchGoodsDO updateObj = BranchGoodsConvert.INSTANCE.convert(updateReqVO);
        branchGoodsMapper.updateById(updateObj);
    }

    @Override
    public void deleteBranchGoods(Long id) {
        // 校验存在
        this.validateBranchGoodsExists(id);
        // 删除
        branchGoodsMapper.deleteById(id);
    }

    private void validateBranchGoodsExists(Long id) {
        if (branchGoodsMapper.selectById(id) == null) {
            throw exception(BRANCH_GOODS_NOT_EXISTS);
        }
    }

    @Override
    public BranchGoodsDO getBranchGoods(Long id) {
        return branchGoodsMapper.selectById(id);
    }

    @Override
    public List<BranchGoodsDO> getBranchGoodsList(Collection<Long> ids) {
        return branchGoodsMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BranchGoodsDO> getBranchGoodsPage(BranchGoodsPageReqVO pageReqVO) {
        return branchGoodsMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BranchGoodsDO> getBranchGoodsList(BranchGoodsExportReqVO exportReqVO) {
        return branchGoodsMapper.selectList(exportReqVO);
    }

}
