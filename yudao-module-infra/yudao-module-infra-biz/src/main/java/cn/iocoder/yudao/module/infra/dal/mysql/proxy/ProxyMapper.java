package cn.iocoder.yudao.module.infra.dal.mysql.proxy;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.infra.dal.dataobject.proxy.ProxyDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.infra.controller.admin.proxy.vo.*;

/**
 * 网络代理 Mapper
 *
 * @author ruanzh
 */
@Mapper
public interface ProxyMapper extends BaseMapperX<ProxyDO> {

    default PageResult<ProxyDO> selectPage(ProxyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProxyDO>()
                .likeIfPresent(ProxyDO::getName, reqVO.getName())
                .eqIfPresent(ProxyDO::getProtocol, reqVO.getProtocol())
                .eqIfPresent(ProxyDO::getIp, reqVO.getIp())
                .eqIfPresent(ProxyDO::getPort, reqVO.getPort())
                .eqIfPresent(ProxyDO::getAuth, reqVO.getAuth())
                .likeIfPresent(ProxyDO::getUsername, reqVO.getUsername())
                .eqIfPresent(ProxyDO::getPassword, reqVO.getPassword())
                .eqIfPresent(ProxyDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ProxyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProxyDO::getId));
    }

    default List<ProxyDO> selectList(ProxyExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProxyDO>()
                .likeIfPresent(ProxyDO::getName, reqVO.getName())
                .eqIfPresent(ProxyDO::getProtocol, reqVO.getProtocol())
                .eqIfPresent(ProxyDO::getIp, reqVO.getIp())
                .eqIfPresent(ProxyDO::getPort, reqVO.getPort())
                .eqIfPresent(ProxyDO::getAuth, reqVO.getAuth())
                .likeIfPresent(ProxyDO::getUsername, reqVO.getUsername())
                .eqIfPresent(ProxyDO::getPassword, reqVO.getPassword())
                .eqIfPresent(ProxyDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ProxyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProxyDO::getId));
    }

}
