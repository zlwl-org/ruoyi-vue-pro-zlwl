package cn.iocoder.yudao.module.shop.service.member;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.shop.controller.admin.member.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.MemberAccountLogDO;
import cn.iocoder.yudao.module.shop.dal.mysql.member.MemberAccountLogMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link MemberAccountLogServiceImpl} 的单元测试类
*
* @author ZLWL
*/
@Import(MemberAccountLogServiceImpl.class)
public class MemberAccountLogServiceImplTest extends BaseDbUnitTest {

    @Resource
    private MemberAccountLogServiceImpl memberAccountLogService;

    @Resource
    private MemberAccountLogMapper memberAccountLogMapper;

    @Test
    public void testCreateMemberAccountLog_success() {
        // 准备参数
        MemberAccountLogCreateReqVO reqVO = randomPojo(MemberAccountLogCreateReqVO.class);

        // 调用
        Long memberAccountLogId = memberAccountLogService.createMemberAccountLog(reqVO);
        // 断言
        assertNotNull(memberAccountLogId);
        // 校验记录的属性是否正确
        MemberAccountLogDO memberAccountLog = memberAccountLogMapper.selectById(memberAccountLogId);
        assertPojoEquals(reqVO, memberAccountLog);
    }

    @Test
    public void testUpdateMemberAccountLog_success() {
        // mock 数据
        MemberAccountLogDO dbMemberAccountLog = randomPojo(MemberAccountLogDO.class);
        memberAccountLogMapper.insert(dbMemberAccountLog);// @Sql: 先插入出一条存在的数据
        // 准备参数
        MemberAccountLogUpdateReqVO reqVO = randomPojo(MemberAccountLogUpdateReqVO.class, o -> {
            o.setId(dbMemberAccountLog.getId()); // 设置更新的 ID
        });

        // 调用
        memberAccountLogService.updateMemberAccountLog(reqVO);
        // 校验是否更新正确
        MemberAccountLogDO memberAccountLog = memberAccountLogMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, memberAccountLog);
    }

    @Test
    public void testUpdateMemberAccountLog_notExists() {
        // 准备参数
        MemberAccountLogUpdateReqVO reqVO = randomPojo(MemberAccountLogUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> memberAccountLogService.updateMemberAccountLog(reqVO), MEMBER_ACCOUNT_LOG_NOT_EXISTS);
    }

    @Test
    public void testDeleteMemberAccountLog_success() {
        // mock 数据
        MemberAccountLogDO dbMemberAccountLog = randomPojo(MemberAccountLogDO.class);
        memberAccountLogMapper.insert(dbMemberAccountLog);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbMemberAccountLog.getId();

        // 调用
        memberAccountLogService.deleteMemberAccountLog(id);
       // 校验数据不存在了
       assertNull(memberAccountLogMapper.selectById(id));
    }

    @Test
    public void testDeleteMemberAccountLog_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> memberAccountLogService.deleteMemberAccountLog(id), MEMBER_ACCOUNT_LOG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMemberAccountLogPage() {
       // mock 数据
       MemberAccountLogDO dbMemberAccountLog = randomPojo(MemberAccountLogDO.class, o -> { // 等会查询到
           o.setMemberId(null);
           o.setAction(null);
           o.setBalance(null);
           o.setGift(null);
           o.setPoint(null);
           o.setGrowth(null);
           o.setInfo(null);
           o.setCreateTime(null);
       });
       memberAccountLogMapper.insert(dbMemberAccountLog);
       // 测试 memberId 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setMemberId(null)));
       // 测试 action 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setAction(null)));
       // 测试 balance 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setBalance(null)));
       // 测试 gift 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setGift(null)));
       // 测试 point 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setPoint(null)));
       // 测试 growth 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setGrowth(null)));
       // 测试 info 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setInfo(null)));
       // 测试 createTime 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setCreateTime(null)));
       // 准备参数
       MemberAccountLogPageReqVO reqVO = new MemberAccountLogPageReqVO();
       reqVO.setMemberId(null);
       reqVO.setAction(null);
       reqVO.setBalance(null);
       reqVO.setGift(null);
       reqVO.setPoint(null);
       reqVO.setGrowth(null);
       reqVO.setInfo(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<MemberAccountLogDO> pageResult = memberAccountLogService.getMemberAccountLogPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbMemberAccountLog, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMemberAccountLogList() {
       // mock 数据
       MemberAccountLogDO dbMemberAccountLog = randomPojo(MemberAccountLogDO.class, o -> { // 等会查询到
           o.setMemberId(null);
           o.setAction(null);
           o.setBalance(null);
           o.setGift(null);
           o.setPoint(null);
           o.setGrowth(null);
           o.setInfo(null);
           o.setCreateTime(null);
       });
       memberAccountLogMapper.insert(dbMemberAccountLog);
       // 测试 memberId 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setMemberId(null)));
       // 测试 action 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setAction(null)));
       // 测试 balance 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setBalance(null)));
       // 测试 gift 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setGift(null)));
       // 测试 point 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setPoint(null)));
       // 测试 growth 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setGrowth(null)));
       // 测试 info 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setInfo(null)));
       // 测试 createTime 不匹配
       memberAccountLogMapper.insert(cloneIgnoreId(dbMemberAccountLog, o -> o.setCreateTime(null)));
       // 准备参数
       MemberAccountLogExportReqVO reqVO = new MemberAccountLogExportReqVO();
       reqVO.setMemberId(null);
       reqVO.setAction(null);
       reqVO.setBalance(null);
       reqVO.setGift(null);
       reqVO.setPoint(null);
       reqVO.setGrowth(null);
       reqVO.setInfo(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<MemberAccountLogDO> list = memberAccountLogService.getMemberAccountLogList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbMemberAccountLog, list.get(0));
    }

}
