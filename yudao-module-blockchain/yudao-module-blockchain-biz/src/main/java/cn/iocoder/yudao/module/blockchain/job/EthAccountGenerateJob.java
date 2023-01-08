package cn.iocoder.yudao.module.blockchain.job;

import cn.iocoder.yudao.framework.quartz.core.handler.JobHandler;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthAccountCreateReqVO;
import cn.iocoder.yudao.module.blockchain.service.eth.EthAccountService;
import cn.iocoder.yudao.module.blockchain.util.MnemonicUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.crypto.ECKeyPair;

import javax.annotation.Resource;

import static cn.iocoder.yudao.module.blockchain.enums.CoinType.eth_string;

@Component
@Slf4j
public class EthAccountGenerateJob implements JobHandler {

    @Resource
    private EthAccountService ethAccountService;

    @Override
    public String execute(String param) throws Exception {
        log.info("EthAccountGenerateJob start");
        for (int i = 0; i <60; i++) {
            String mnemonic = MnemonicUtil.randomMnemonic();
            ECKeyPair keyPair = MnemonicUtil.createDefaultKeyFromMnemonic(mnemonic, eth_string);

            EthAccountCreateReqVO create = new EthAccountCreateReqVO();
            create.setMnemonic(mnemonic);
            create.setAddress(MnemonicUtil.getDefaultAddressFromMnemonic(mnemonic, eth_string));
            create.setPrivateKey("0x" + String.format("%64s", keyPair.getPrivateKey().toString(16)).replace(" ", "0"));
            create.setOwned(false);

            ethAccountService.createEthAccount(create);
        }
        log.info("EthAccountGenerateJob ended");
        return "创建60个地址";
    }
}
