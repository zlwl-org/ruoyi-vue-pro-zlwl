package cn.iocoder.yudao.module.shop.service.recharge;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeCreateReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargePageReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeUpdateReqVO;
import cn.iocoder.yudao.module.shop.convert.recharge.RechargeConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeDO;
import cn.iocoder.yudao.module.shop.dal.mysql.recharge.RechargeMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.RECHARGE_NOT_EXISTS;

/**
 * 充值活动 Service 实现类
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

    @Override
    public List<RechargeDO> getAll() {
        return rechargeMapper.selectList();
    }

    @Override
    public RechargeDO getBestRecharge(BigDecimal amount) {
        List<RechargeDO> list = rechargeMapper.selectActiveList();
        List<RechargeDO> collected = list.stream().filter(item -> item.getPrice().compareTo(amount) <= 0).toList();
        if (collected.size() == 0){
            return null;
        }
        return collected.stream().max(Comparator.comparing(RechargeDO::getGift)).get();
    }

    @Override
    public List<RechargeDO> todayOrder() {
        return rechargeMapper.selectTodayOrder();
    }

}
