package cn.iocoder.yudao.module.infra.service.proxy;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.infra.controller.admin.proxy.vo.*;
import cn.iocoder.yudao.module.infra.dal.dataobject.proxy.ProxyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.infra.convert.proxy.ProxyConvert;
import cn.iocoder.yudao.module.infra.dal.mysql.proxy.ProxyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.infra.enums.ErrorCodeConstants.*;

/**
 * 网络代理 Service 实现类
 *
 * @author ruanzh
 */
@Service
@Validated
public class ProxyServiceImpl implements ProxyService {

    @Resource
    private ProxyMapper proxyMapper;

    @Override
    public Long createProxy(ProxyCreateReqVO createReqVO) {
        // 插入
        ProxyDO proxy = ProxyConvert.INSTANCE.convert(createReqVO);
        proxyMapper.insert(proxy);
        // 返回
        return proxy.getId();
    }

    @Override
    public void updateProxy(ProxyUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateProxyExists(updateReqVO.getId());
        // 更新
        ProxyDO updateObj = ProxyConvert.INSTANCE.convert(updateReqVO);
        proxyMapper.updateById(updateObj);
    }

    @Override
    public void deleteProxy(Long id) {
        // 校验存在
        this.validateProxyExists(id);
        // 删除
        proxyMapper.deleteById(id);
    }

    private void validateProxyExists(Long id) {
        if (proxyMapper.selectById(id) == null) {
            throw exception(PROXY_NOT_EXISTS);
        }
    }

    @Override
    public ProxyDO getProxy(Long id) {
        return proxyMapper.selectById(id);
    }

    @Override
    public List<ProxyDO> getProxyList(Collection<Long> ids) {
        return proxyMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProxyDO> getProxyPage(ProxyPageReqVO pageReqVO) {
        return proxyMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProxyDO> getProxyList(ProxyExportReqVO exportReqVO) {
        return proxyMapper.selectList(exportReqVO);
    }

}
