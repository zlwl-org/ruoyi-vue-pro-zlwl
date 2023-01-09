package cn.iocoder.yudao.module.blockchain.job;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.quartz.core.handler.JobHandler;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthMainNetAddressCreateReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.event.vo.EventCreateReqVO;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthAccountDO;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthMainNetAddressDO;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.infra.NetDO;
import cn.iocoder.yudao.module.blockchain.service.eth.EthAccountService;
import cn.iocoder.yudao.module.blockchain.service.eth.EthMainNetAddressService;
import cn.iocoder.yudao.module.blockchain.service.event.EventService;
import cn.iocoder.yudao.module.blockchain.service.infra.NetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.http.HttpService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Component
@Slf4j
public class EthMainNetAddressSyncJob implements JobHandler {

    @Resource
    private EthAccountService ethAccountService;
    @Resource
    private EthMainNetAddressService mainNetAddressService;

    @Resource
    private NetService netService;

    @Resource
    private EventService eventService;

    @Override
    public String execute(String param) throws Exception {
        log.info("EthMainNetAddressSyncJob start");
        EthMainNetAddressDO latest = mainNetAddressService.getLatest();
        List<EthAccountDO> ethAccountList;
        if (latest == null){
            ethAccountList = ethAccountService.getEthAccountList();
        } else {
            ethAccountList = ethAccountService.getEthAccountList(latest.getCreateTime());
        }

        if (ethAccountList == null) {
            return "同步完成：无新增账户";
        }

        NetDO net = netService.getNet("ETH");
        if (net == null){
            return "同步失败：netService.getNet(ETH)为空;";
        }
        Web3j web3j = Web3j.build(new HttpService(net.getPublicRpc()));
        for (EthAccountDO ethAccountDO : ethAccountList) {
            EthMainNetAddressCreateReqVO create = new EthMainNetAddressCreateReqVO();
            create.setAddress(ethAccountDO.getAddress());
            BigInteger balance = web3j.ethGetBalance(ethAccountDO.getAddress(), DefaultBlockParameterName.LATEST).send().getBalance();
            create.setBalance(new BigDecimal(balance).divide(BigDecimal.TEN.pow(18)));
            mainNetAddressService.createEthMainNetAddress(create);

            if (balance.compareTo(BigInteger.ZERO) != 0){
                EventCreateReqVO event = new EventCreateReqVO();
                event.setTopic("account found");
                event.setNet("Eth MainNet");
                event.setAddress(ethAccountDO.getAddress());
                event.setInfo(StrUtil.format("账户余额：{}", create.getBalance()));
                eventService.createEvent(event);
            }
        }
        log.info("EthMainNetAddressSyncJob ended");
        return StrUtil.format("同步完成：同步{}个账户", ethAccountList.size());
    }
}
