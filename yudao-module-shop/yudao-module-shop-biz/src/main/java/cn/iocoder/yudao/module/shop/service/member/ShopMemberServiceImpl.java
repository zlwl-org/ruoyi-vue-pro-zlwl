package cn.iocoder.yudao.module.shop.service.member;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.shop.convert.member.ShopMemberConvert;
import cn.iocoder.yudao.module.shop.dal.mysql.member.ShopMemberMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.*;

/**
 * 会员 Service 实现类
 *
 * @author ZLWL
 */
@Service
@Validated
public class ShopMemberServiceImpl implements ShopMemberService {

    @Resource
    private ShopMemberMapper memberMapper;

    @Override
    public Long createMember(ShopMemberCreateReqVO createReqVO) {
        // 插入
        ShopMemberDO member = ShopMemberConvert.INSTANCE.convert(createReqVO);
        memberMapper.insert(member);
        // 返回
        return member.getId();
    }

    @Override
    public void updateMember(ShopMemberUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateMemberExists(updateReqVO.getId());
        // 更新
        ShopMemberDO updateObj = ShopMemberConvert.INSTANCE.convert(updateReqVO);
        memberMapper.updateById(updateObj);
    }

    @Override
    public void deleteMember(Long id) {
        // 校验存在
        this.validateMemberExists(id);
        // 删除
        memberMapper.deleteById(id);
    }

    private void validateMemberExists(Long id) {
        if (memberMapper.selectById(id) == null) {
            throw exception(MEMBER_NOT_EXISTS);
        }
    }

    @Override
    public ShopMemberDO getMember(Long id) {
        return memberMapper.selectById(id);
    }

    @Override
    public List<ShopMemberDO> getMemberList(Collection<Long> ids) {
        return memberMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ShopMemberDO> getMemberPage(ShopMemberPageReqVO pageReqVO) {
        return memberMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ShopMemberDO> getMemberList(ShopMemberExportReqVO exportReqVO) {
        return memberMapper.selectList(exportReqVO);
    }

}
