package cn.iocoder.yudao.module.infra.service.proxy;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.infra.controller.admin.proxy.vo.*;
import cn.iocoder.yudao.module.infra.dal.dataobject.proxy.ProxyDO;
import cn.iocoder.yudao.module.infra.dal.mysql.proxy.ProxyMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.infra.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link ProxyServiceImpl} 的单元测试类
*
* @author ruanzh
*/
@Import(ProxyServiceImpl.class)
public class ProxyServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ProxyServiceImpl proxyService;

    @Resource
    private ProxyMapper proxyMapper;

    @Test
    public void testCreateProxy_success() {
        // 准备参数
        ProxyCreateReqVO reqVO = randomPojo(ProxyCreateReqVO.class);

        // 调用
        Long proxyId = proxyService.createProxy(reqVO);
        // 断言
        assertNotNull(proxyId);
        // 校验记录的属性是否正确
        ProxyDO proxy = proxyMapper.selectById(proxyId);
        assertPojoEquals(reqVO, proxy);
    }

    @Test
    public void testUpdateProxy_success() {
        // mock 数据
        ProxyDO dbProxy = randomPojo(ProxyDO.class);
        proxyMapper.insert(dbProxy);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ProxyUpdateReqVO reqVO = randomPojo(ProxyUpdateReqVO.class, o -> {
            o.setId(dbProxy.getId()); // 设置更新的 ID
        });

        // 调用
        proxyService.updateProxy(reqVO);
        // 校验是否更新正确
        ProxyDO proxy = proxyMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, proxy);
    }

    @Test
    public void testUpdateProxy_notExists() {
        // 准备参数
        ProxyUpdateReqVO reqVO = randomPojo(ProxyUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> proxyService.updateProxy(reqVO), PROXY_NOT_EXISTS);
    }

    @Test
    public void testDeleteProxy_success() {
        // mock 数据
        ProxyDO dbProxy = randomPojo(ProxyDO.class);
        proxyMapper.insert(dbProxy);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbProxy.getId();

        // 调用
        proxyService.deleteProxy(id);
       // 校验数据不存在了
       assertNull(proxyMapper.selectById(id));
    }

    @Test
    public void testDeleteProxy_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> proxyService.deleteProxy(id), PROXY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetProxyPage() {
       // mock 数据
       ProxyDO dbProxy = randomPojo(ProxyDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setProtocol(null);
           o.setIp(null);
           o.setPort(null);
           o.setAuth(null);
           o.setUsername(null);
           o.setPassword(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       proxyMapper.insert(dbProxy);
       // 测试 name 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setName(null)));
       // 测试 protocol 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setProtocol(null)));
       // 测试 ip 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setIp(null)));
       // 测试 port 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setPort(null)));
       // 测试 auth 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setAuth(null)));
       // 测试 username 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setUsername(null)));
       // 测试 password 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setPassword(null)));
       // 测试 status 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setCreateTime(null)));
       // 准备参数
       ProxyPageReqVO reqVO = new ProxyPageReqVO();
       reqVO.setName(null);
       reqVO.setProtocol(null);
       reqVO.setIp(null);
       reqVO.setPort(null);
       reqVO.setAuth(null);
       reqVO.setUsername(null);
       reqVO.setPassword(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<ProxyDO> pageResult = proxyService.getProxyPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbProxy, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetProxyList() {
       // mock 数据
       ProxyDO dbProxy = randomPojo(ProxyDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setProtocol(null);
           o.setIp(null);
           o.setPort(null);
           o.setAuth(null);
           o.setUsername(null);
           o.setPassword(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       proxyMapper.insert(dbProxy);
       // 测试 name 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setName(null)));
       // 测试 protocol 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setProtocol(null)));
       // 测试 ip 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setIp(null)));
       // 测试 port 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setPort(null)));
       // 测试 auth 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setAuth(null)));
       // 测试 username 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setUsername(null)));
       // 测试 password 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setPassword(null)));
       // 测试 status 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       proxyMapper.insert(cloneIgnoreId(dbProxy, o -> o.setCreateTime(null)));
       // 准备参数
       ProxyExportReqVO reqVO = new ProxyExportReqVO();
       reqVO.setName(null);
       reqVO.setProtocol(null);
       reqVO.setIp(null);
       reqVO.setPort(null);
       reqVO.setAuth(null);
       reqVO.setUsername(null);
       reqVO.setPassword(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<ProxyDO> list = proxyService.getProxyList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbProxy, list.get(0));
    }

}
