package cn.iocoder.yudao.module.infra.service.proxy;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.infra.controller.admin.proxy.vo.*;
import cn.iocoder.yudao.module.infra.dal.dataobject.proxy.ProxyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 网络代理 Service 接口
 *
 * @author ruanzh
 */
public interface ProxyService {

    /**
     * 创建网络代理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProxy(@Valid ProxyCreateReqVO createReqVO);

    /**
     * 更新网络代理
     *
     * @param updateReqVO 更新信息
     */
    void updateProxy(@Valid ProxyUpdateReqVO updateReqVO);

    /**
     * 删除网络代理
     *
     * @param id 编号
     */
    void deleteProxy(Long id);

    /**
     * 获得网络代理
     *
     * @param id 编号
     * @return 网络代理
     */
    ProxyDO getProxy(Long id);

    /**
     * 获得网络代理列表
     *
     * @param ids 编号
     * @return 网络代理列表
     */
    List<ProxyDO> getProxyList(Collection<Long> ids);

    /**
     * 获得网络代理分页
     *
     * @param pageReqVO 分页查询
     * @return 网络代理分页
     */
    PageResult<ProxyDO> getProxyPage(ProxyPageReqVO pageReqVO);

    /**
     * 获得网络代理列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 网络代理列表
     */
    List<ProxyDO> getProxyList(ProxyExportReqVO exportReqVO);

}
