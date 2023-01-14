package cn.iocoder.yudao.module.blockchain.service.account;

import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthAccountCreateReqVO;
import cn.iocoder.yudao.module.blockchain.service.eth.EthAccountService;
import cn.iocoder.yudao.module.blockchain.util.MnemonicUtil;
import org.springframework.stereotype.Component;
import org.web3j.crypto.ECKeyPair;

import javax.annotation.Resource;

import static cn.iocoder.yudao.module.blockchain.enums.CoinType.eth_string;

/**
 * eth帐户生成器impl
 *
 * @author ruanzh.eth
 * @date 2023/01/14
 */
@Component
public class EthAccountGeneratorImpl implements AccountGeneratorInterface {

    @Resource
    private EthAccountService ethAccountService;

    @Override
    public String getChainTech() {
        return eth_string;
    }

    @Override
    public Account generate(String mnemonic) {
        if (mnemonic == null) {
            mnemonic = MnemonicUtil.randomMnemonic();
        }
        Account account = new Account();
        ECKeyPair keyPair = MnemonicUtil.createDefaultKeyFromMnemonic(mnemonic, getChainTech());
        account.setMnemonic(mnemonic);
        account.setKeyPair(keyPair);
        account.setAddress(MnemonicUtil.getDefaultAddressFromMnemonic(mnemonic, getChainTech()));
        account.setPrivateKey(MnemonicUtil.getPrivateKeyFromKeyPair(keyPair, getChainTech()));

        return account;
    }

    @Override
    public void save(Account account, Boolean owned) {
        EthAccountCreateReqVO create = new EthAccountCreateReqVO();
        create.setMnemonic(account.getMnemonic());
        create.setAddress(account.getAddress());
        create.setPrivateKey(account.getPrivateKey());
        create.setOwned(owned);

        ethAccountService.createEthAccount(create);
    }
}
