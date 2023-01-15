package cn.iocoder.yudao.module.blockchain.job;

import cn.iocoder.yudao.framework.quartz.core.handler.JobHandler;
import cn.iocoder.yudao.module.blockchain.convert.eth.EthBlockConvert;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthBlockDO;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.infra.NetDO;
import cn.iocoder.yudao.module.blockchain.service.eth.EthBlockService;
import cn.iocoder.yudao.module.blockchain.service.infra.NetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;

import javax.annotation.Resource;

@Component
@Slf4j
public class EthBlockSyncJob implements JobHandler {
    private final String netSymbol = "Eth";
    @Resource
    private NetService netService;
    @Resource
    private EthBlockService ethBlockService;

    @Override
    public String execute(String param) throws Exception {
        log.info("EthBlockSyncJob start");
        NetDO net = netService.getNet(netSymbol);
        if (net == null) {
            return "同步失败：netService.getNet(ETH)为空;";
        }
        EthBlockDO latest = ethBlockService.getLatest();
        Web3j web3j = Web3j.build(new HttpService(net.getPublicRpc()));
        if (latest == null) {
//            web3j.block
        }
        EthBlock ethBlock = web3j.ethGetBlockByNumber(new DefaultBlockParameterNumber(1), false).send();
        EthBlock.Block result = ethBlock.getResult();

        EthBlockDO block = EthBlockConvert.INSTANCE.convert(result);
        ethBlockService.save(block);
        log.info("EthBlockSyncJob ended");
        return "同步成功";
    }
}
