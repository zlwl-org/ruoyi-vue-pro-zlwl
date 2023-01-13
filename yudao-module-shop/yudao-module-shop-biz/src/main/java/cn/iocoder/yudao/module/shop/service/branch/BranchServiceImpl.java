package cn.iocoder.yudao.module.shop.service.branch;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.shop.convert.branch.BranchConvert;
import cn.iocoder.yudao.module.shop.dal.mysql.branch.BranchMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.*;

/**
 * 门店 Service 实现类
 *
 * @author ZLWL
 */
@Service
@Validated
public class BranchServiceImpl implements BranchService {

    @Resource
    private BranchMapper branchMapper;

    @Override
    public Long createBranch(BranchCreateReqVO createReqVO) {
        // 插入
        BranchDO branch = BranchConvert.INSTANCE.convert(createReqVO);
        branchMapper.insert(branch);
        // 返回
        return branch.getId();
    }

    @Override
    public void updateBranch(BranchUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateBranchExists(updateReqVO.getId());
        // 更新
        BranchDO updateObj = BranchConvert.INSTANCE.convert(updateReqVO);
        branchMapper.updateById(updateObj);
    }

    @Override
    public void deleteBranch(Long id) {
        // 校验存在
        this.validateBranchExists(id);
        // 删除
        branchMapper.deleteById(id);
    }

    private void validateBranchExists(Long id) {
        if (branchMapper.selectById(id) == null) {
            throw exception(BRANCH_NOT_EXISTS);
        }
    }

    @Override
    public BranchDO getBranch(Long id) {
        return branchMapper.selectById(id);
    }

    @Override
    public List<BranchDO> getBranchList(Collection<Long> ids) {
        return branchMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BranchDO> getBranchPage(BranchPageReqVO pageReqVO) {
        return branchMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BranchDO> getBranchList(BranchExportReqVO exportReqVO) {
        return branchMapper.selectList(exportReqVO);
    }

    @Override
    public List<BranchDO> getBranchesByStatus(Integer status) {
        return branchMapper.selectListByStatus(status);
    }

}
