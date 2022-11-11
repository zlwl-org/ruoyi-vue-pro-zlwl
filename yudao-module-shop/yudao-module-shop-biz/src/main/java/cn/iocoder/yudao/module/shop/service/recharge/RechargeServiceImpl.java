package cn.iocoder.yudao.module.shop.service.recharge;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.shop.convert.recharge.RechargeConvert;
import cn.iocoder.yudao.module.shop.dal.mysql.recharge.RechargeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.*;

/**
 * 会员充值套餐 Service 实现类
 *
 * @author ZLWL
 */
@Service
@Validated
public class RechargeServiceImpl implements RechargeService {

    @Resource
    private RechargeMapper rechargeMapper;

    @Override
    public Long createRecharge(RechargeCreateReqVO createReqVO) {
        // 插入
        RechargeDO recharge = RechargeConvert.INSTANCE.convert(createReqVO);
        rechargeMapper.insert(recharge);
        // 返回
        return recharge.getId();
    }

    @Override
    public void updateRecharge(RechargeUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateRechargeExists(updateReqVO.getId());
        // 更新
        RechargeDO updateObj = RechargeConvert.INSTANCE.convert(updateReqVO);
        rechargeMapper.updateById(updateObj);
    }

    @Override
    public void deleteRecharge(Long id) {
        // 校验存在
        this.validateRechargeExists(id);
        // 删除
        rechargeMapper.deleteById(id);
    }

    private void validateRechargeExists(Long id) {
        if (rechargeMapper.selectById(id) == null) {
            throw exception(RECHARGE_NOT_EXISTS);
        }
    }

    @Override
    public RechargeDO getRecharge(Long id) {
        return rechargeMapper.selectById(id);
    }

    @Override
    public List<RechargeDO> getRechargeList(Collection<Long> ids) {
        return rechargeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RechargeDO> getRechargePage(RechargePageReqVO pageReqVO) {
        return rechargeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RechargeDO> getRechargeList(RechargeExportReqVO exportReqVO) {
        return rechargeMapper.selectList(exportReqVO);
    }

}
