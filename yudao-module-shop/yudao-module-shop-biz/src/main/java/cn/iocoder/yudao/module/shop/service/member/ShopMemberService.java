package cn.iocoder.yudao.module.shop.service.member;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 会员 Service 接口
 *
 * @author ZLWL
 */
public interface ShopMemberService {

    /**
     * 创建会员
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMember(@Valid ShopMemberCreateReqVO createReqVO);

    /**
     * 更新会员
     *
     * @param updateReqVO 更新信息
     */
    void updateMember(@Valid ShopMemberUpdateReqVO updateReqVO);

    /**
     * 删除会员
     *
     * @param id 编号
     */
    void deleteMember(Long id);

    /**
     * 获得会员
     *
     * @param id 编号
     * @return 会员
     */
    ShopMemberDO getMember(Long id);

    /**
     * 获得会员列表
     *
     * @param ids 编号
     * @return 会员列表
     */
    List<ShopMemberDO> getMemberList(Collection<Long> ids);

    /**
     * 获得会员分页
     *
     * @param pageReqVO 分页查询
     * @return 会员分页
     */
    PageResult<ShopMemberDO> getMemberPage(ShopMemberPageReqVO pageReqVO);

    /**
     * 获得会员列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 会员列表
     */
    List<ShopMemberDO> getMemberList(ShopMemberExportReqVO exportReqVO);

}
