package cn.iocoder.yudao.module.shop.service.member;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.MemberAccountLogDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 会员账户流水 Service 接口
 *
 * @author ZLWL
 */
public interface MemberAccountLogService {

    /**
     * 创建会员账户流水
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMemberAccountLog(@Valid MemberAccountLogCreateReqVO createReqVO);

    /**
     * 更新会员账户流水
     *
     * @param updateReqVO 更新信息
     */
    void updateMemberAccountLog(@Valid MemberAccountLogUpdateReqVO updateReqVO);

    /**
     * 删除会员账户流水
     *
     * @param id 编号
     */
    void deleteMemberAccountLog(Long id);

    /**
     * 获得会员账户流水
     *
     * @param id 编号
     * @return 会员账户流水
     */
    MemberAccountLogDO getMemberAccountLog(Long id);

    /**
     * 获得会员账户流水列表
     *
     * @param ids 编号
     * @return 会员账户流水列表
     */
    List<MemberAccountLogDO> getMemberAccountLogList(Collection<Long> ids);

    /**
     * 获得会员账户流水分页
     *
     * @param pageReqVO 分页查询
     * @return 会员账户流水分页
     */
    PageResult<MemberAccountLogDO> getMemberAccountLogPage(MemberAccountLogPageReqVO pageReqVO);

    /**
     * 获得会员账户流水列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 会员账户流水列表
     */
    List<MemberAccountLogDO> getMemberAccountLogList(MemberAccountLogExportReqVO exportReqVO);

}
