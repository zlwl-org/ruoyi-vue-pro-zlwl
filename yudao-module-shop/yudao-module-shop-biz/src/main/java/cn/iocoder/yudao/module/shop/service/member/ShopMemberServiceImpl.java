package cn.iocoder.yudao.module.shop.service.member;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.ShopMemberCreateReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.ShopMemberExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.ShopMemberPageReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.ShopMemberUpdateReqVO;
import cn.iocoder.yudao.module.shop.convert.member.ShopMemberConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import cn.iocoder.yudao.module.shop.dal.mysql.member.ShopMemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.MEMBER_BALANCE_NOT_ENOUGH;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.MEMBER_NOT_EXISTS;

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
        ShopMemberDO member = memberMapper.selectById(id);
        if (member == null){
            throw exception(MEMBER_NOT_EXISTS);
        }
        return member;
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

    @Override
    public List<ShopMemberDO> getMemberListByUser() {
        //TODO: 会员列表还没有控制权限
        return memberMapper.selectList();
    }

    @Override
    public int updateMemberAccount(BigDecimal balance, BigDecimal gift, BigDecimal point, BigDecimal growth, Long memberId) {
        int result = memberMapper.updateAccount(balance, gift, point, growth, memberId);
        if (result == 0){
            throw exception(MEMBER_BALANCE_NOT_ENOUGH);
        }
        return result;
    }

    @Override
    public int updateMemberBalance(Long memberId, BigDecimal balanceChange) {
        int result = memberMapper.updateBalance(memberId, balanceChange);
        if (result == 0){
            throw exception(MEMBER_BALANCE_NOT_ENOUGH);
        }
        return result;
    }

}
