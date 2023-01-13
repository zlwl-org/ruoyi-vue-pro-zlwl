package cn.iocoder.yudao.module.blockchain.service.infra;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo.NetCreateReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo.NetExportReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo.NetPageReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo.NetUpdateReqVO;
import cn.iocoder.yudao.module.blockchain.convert.infra.NetConvert;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.infra.NetDO;
import cn.iocoder.yudao.module.blockchain.dal.mysql.infra.NetMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.blockchain.enums.ErrorCodeConstants.NET_NOT_EXISTS;

/**
 * 网络 Service 实现类
 *
 * @author ruanzh
 */
@Service
@Validated
public class NetServiceImpl implements NetService {

    @Resource
    private NetMapper netMapper;

    @Override
    public Long createNet(NetCreateReqVO createReqVO) {
        // 检查网络连通性
//        createReqVO.getPublicRpc()

//        final int proxyPort = 38438; //your proxy port
//        final String proxyHost = "45.128.209.151";
//        final String username = "YFtPDDgPIm";
//        final String password = "PgqFstPI1e";
//        InetSocketAddress proxyAddr = new InetSocketAddress(proxyHost, proxyPort);
//        Proxy proxy = new Proxy(Proxy.Type.SOCKS, proxyAddr);
//
//        Authenticator.setDefault(new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                if (getRequestingHost().equalsIgnoreCase(proxyHost)) {
//                    if (proxyPort == getRequestingPort()) {
//                        return new PasswordAuthentication(username, password.toCharArray());
//                    }
//                }
//                return null;
//            }
//        });
//
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .proxy(proxy)
//                .build();
//
//        try (Response response = client.newCall(new Request.Builder().url("https://www.google.com").build()).execute()) {
////            log.info(response.body().string());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        // 检查默认节点
        Web3j web3j = Web3j.build(new HttpService(createReqVO.getPublicRpc()));
        long id = 0;
        try {
            id = web3j.ethChainId().send().getChainId().intValue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (id - createReqVO.getChainId() != 0) {
            throw new IllegalArgumentException("默认节点的链ID与配置的链ID不同,请仔细检查!");
        }
        // 检查私有节点
        if (createReqVO.getPrivateRpc() != null){
            Web3j web3j_1 = Web3j.build(new HttpService(createReqVO.getPrivateRpc()));
            if (web3j_1.ethChainId().getId() - createReqVO.getChainId() != 0) {
                throw new IllegalArgumentException("私密节点的链ID与配置的链ID不同,请仔细检查!");
            }
        }
        // 插入
        NetDO net = NetConvert.INSTANCE.convert(createReqVO);
        netMapper.insert(net);
        // 返回
        return net.getId();
    }

    @Override
    public void updateNet(NetUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateNetExists(updateReqVO.getId());
        // 更新
        NetDO updateObj = NetConvert.INSTANCE.convert(updateReqVO);
        netMapper.updateById(updateObj);
    }

    @Override
    public void deleteNet(Long id) {
        // 校验存在
        this.validateNetExists(id);
        // 删除
        netMapper.deleteById(id);
    }

    private void validateNetExists(Long id) {
        if (netMapper.selectById(id) == null) {
            throw exception(NET_NOT_EXISTS);
        }
    }

    @Override
    public NetDO getNet(Long id) {
        return netMapper.selectById(id);
    }

    @Override
    public List<NetDO> getNetList(Collection<Long> ids) {
        return netMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<NetDO> getNetPage(NetPageReqVO pageReqVO) {
        return netMapper.selectPage(pageReqVO);
    }

    @Override
    public List<NetDO> getNetList(NetExportReqVO exportReqVO) {
        return netMapper.selectList(exportReqVO);
    }

    @Override
    public NetDO getNet(String symbol) {
        return netMapper.selectOne(NetDO::getSymbol, symbol);
    }

    @Override
    public List<NetDO> getAllNet() {
        return netMapper.selectList();
    }

}
