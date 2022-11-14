package cn.iocoder.yudao.module.shop.service.member;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.MemberAccountLogDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.shop.convert.member.MemberAccountLogConvert;
import cn.iocoder.yudao.module.shop.dal.mysql.member.MemberAccountLogMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.*;

/**
 * 会员账户流水 Service 实现类
 *
 * @author ZLWL
 */
@Service
@Validated
public class MemberAccountLogServiceImpl implements MemberAccountLogService {

    @Resource
    private MemberAccountLogMapper memberAccountLogMapper;

    @Override
    public Long createMemberAccountLog(MemberAccountLogCreateReqVO createReqVO) {
        // 插入
        MemberAccountLogDO memberAccountLog = MemberAccountLogConvert.INSTANCE.convert(createReqVO);
        memberAccountLogMapper.insert(memberAccountLog);
        // 返回
        return memberAccountLog.getId();
    }

    @Override
    public void updateMemberAccountLog(MemberAccountLogUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateMemberAccountLogExists(updateReqVO.getId());
        // 更新
        MemberAccountLogDO updateObj = MemberAccountLogConvert.INSTANCE.convert(updateReqVO);
        memberAccountLogMapper.updateById(updateObj);
    }

    @Override
    public void deleteMemberAccountLog(Long id) {
        // 校验存在
        this.validateMemberAccountLogExists(id);
        // 删除
        memberAccountLogMapper.deleteById(id);
    }

    private void validateMemberAccountLogExists(Long id) {
        if (memberAccountLogMapper.selectById(id) == null) {
            throw exception(MEMBER_ACCOUNT_LOG_NOT_EXISTS);
        }
    }

    @Override
    public MemberAccountLogDO getMemberAccountLog(Long id) {
        return memberAccountLogMapper.selectById(id);
    }

    @Override
    public List<MemberAccountLogDO> getMemberAccountLogList(Collection<Long> ids) {
        return memberAccountLogMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MemberAccountLogDO> getMemberAccountLogPage(MemberAccountLogPageReqVO pageReqVO) {
        return memberAccountLogMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MemberAccountLogDO> getMemberAccountLogList(MemberAccountLogExportReqVO exportReqVO) {
        return memberAccountLogMapper.selectList(exportReqVO);
    }

}
