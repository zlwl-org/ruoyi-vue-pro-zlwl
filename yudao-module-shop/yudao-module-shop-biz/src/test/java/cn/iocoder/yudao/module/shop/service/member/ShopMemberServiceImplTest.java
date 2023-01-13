package cn.iocoder.yudao.module.shop.service.member;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.shop.controller.admin.member.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import cn.iocoder.yudao.module.shop.dal.mysql.member.ShopMemberMapper;
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
* {@link ShopMemberServiceImpl} 的单元测试类
*
* @author ZLWL
*/
@Import(ShopMemberServiceImpl.class)
public class ShopMemberServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ShopMemberServiceImpl memberService;

    @Resource
    private ShopMemberMapper memberMapper;

    @Test
    public void testCreateMember_success() {
        // 准备参数
        ShopMemberCreateReqVO reqVO = randomPojo(ShopMemberCreateReqVO.class);

        // 调用
        Long memberId = memberService.createMember(reqVO);
        // 断言
        assertNotNull(memberId);
        // 校验记录的属性是否正确
        ShopMemberDO member = memberMapper.selectById(memberId);
        assertPojoEquals(reqVO, member);
    }

    @Test
    public void testUpdateMember_success() {
        // mock 数据
        ShopMemberDO dbMember = randomPojo(ShopMemberDO.class);
        memberMapper.insert(dbMember);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ShopMemberUpdateReqVO reqVO = randomPojo(ShopMemberUpdateReqVO.class, o -> {
            o.setId(dbMember.getId()); // 设置更新的 ID
        });

        // 调用
        memberService.updateMember(reqVO);
        // 校验是否更新正确
        ShopMemberDO member = memberMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, member);
    }

    @Test
    public void testUpdateMember_notExists() {
        // 准备参数
        ShopMemberUpdateReqVO reqVO = randomPojo(ShopMemberUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> memberService.updateMember(reqVO), MEMBER_NOT_EXISTS);
    }

    @Test
    public void testDeleteMember_success() {
        // mock 数据
        ShopMemberDO dbMember = randomPojo(ShopMemberDO.class);
        memberMapper.insert(dbMember);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbMember.getId();

        // 调用
        memberService.deleteMember(id);
       // 校验数据不存在了
       assertNull(memberMapper.selectById(id));
    }

    @Test
    public void testDeleteMember_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> memberService.deleteMember(id), MEMBER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMemberPage() {
       // mock 数据
       ShopMemberDO dbMember = randomPojo(ShopMemberDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setNickname(null);
           o.setMobile(null);
           o.setSalesman(null);
           o.setType(null);
           o.setStatus(null);
           o.setBranchId(null);
           o.setCreateTime(null);
       });
       memberMapper.insert(dbMember);
       // 测试 name 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setName(null)));
       // 测试 nickname 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setNickname(null)));
       // 测试 mobile 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setMobile(null)));
       // 测试 salesman 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setSalesman(null)));
       // 测试 type 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setType(null)));
       // 测试 status 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setStatus(null)));
       // 测试 branchId 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setBranchId(null)));
       // 测试 createTime 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setCreateTime(null)));
       // 准备参数
       ShopMemberPageReqVO reqVO = new ShopMemberPageReqVO();
       reqVO.setName(null);
       reqVO.setNickname(null);
       reqVO.setMobile(null);
       reqVO.setSalesman(null);
       reqVO.setType(null);
       reqVO.setStatus(null);
       reqVO.setBranchId(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<ShopMemberDO> pageResult = memberService.getMemberPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbMember, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMemberList() {
       // mock 数据
       ShopMemberDO dbMember = randomPojo(ShopMemberDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setNickname(null);
           o.setMobile(null);
           o.setSalesman(null);
           o.setType(null);
           o.setStatus(null);
           o.setBranchId(null);
           o.setCreateTime(null);
       });
       memberMapper.insert(dbMember);
       // 测试 name 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setName(null)));
       // 测试 nickname 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setNickname(null)));
       // 测试 mobile 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setMobile(null)));
       // 测试 salesman 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setSalesman(null)));
       // 测试 type 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setType(null)));
       // 测试 status 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setStatus(null)));
       // 测试 branchId 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setBranchId(null)));
       // 测试 createTime 不匹配
       memberMapper.insert(cloneIgnoreId(dbMember, o -> o.setCreateTime(null)));
       // 准备参数
       ShopMemberExportReqVO reqVO = new ShopMemberExportReqVO();
       reqVO.setName(null);
       reqVO.setNickname(null);
       reqVO.setMobile(null);
       reqVO.setSalesman(null);
       reqVO.setType(null);
       reqVO.setStatus(null);
       reqVO.setBranchId(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<ShopMemberDO> list = memberService.getMemberList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbMember, list.get(0));
    }

}
