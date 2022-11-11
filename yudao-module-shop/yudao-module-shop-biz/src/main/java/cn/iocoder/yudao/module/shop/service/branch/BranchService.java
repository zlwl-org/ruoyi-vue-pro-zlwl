package cn.iocoder.yudao.module.shop.service.branch;

import java.util.*;
import javax.validation.*;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 门店 Service 接口
 *
 * @author ZLWL
 */
public interface BranchService {

    /**
     * 创建门店
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBranch(@Valid BranchCreateReqVO createReqVO);

    /**
     * 更新门店
     *
     * @param updateReqVO 更新信息
     */
    void updateBranch(@Valid BranchUpdateReqVO updateReqVO);

    /**
     * 删除门店
     *
     * @param id 编号
     */
    void deleteBranch(Long id);

    /**
     * 获得门店
     *
     * @param id 编号
     * @return 门店
     */
    BranchDO getBranch(Long id);

    /**
     * 获得门店列表
     *
     * @param ids 编号
     * @return 门店列表
     */
    List<BranchDO> getBranchList(Collection<Long> ids);

    /**
     * 获得门店分页
     *
     * @param pageReqVO 分页查询
     * @return 门店分页
     */
    PageResult<BranchDO> getBranchPage(BranchPageReqVO pageReqVO);

    /**
     * 获得门店列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 门店列表
     */
    List<BranchDO> getBranchList(BranchExportReqVO exportReqVO);

    List<BranchDO> getBranchesByStatus(Integer status);
}
