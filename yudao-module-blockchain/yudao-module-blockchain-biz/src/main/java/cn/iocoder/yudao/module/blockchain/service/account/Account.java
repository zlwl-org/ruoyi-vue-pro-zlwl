package cn.iocoder.yudao.module.blockchain.service.account;

import lombok.Data;
import org.web3j.crypto.ECKeyPair;


/**
 * 账户
 *
 * @author ruanzh.eth
 * @date 2023/01/14
 */
@Data
public class Account {
    /** 助记词 */
    private String mnemonic;
    /** 地址 */
    private String address;
    /** 私钥 */
    private String privateKey;
    /** 密钥对 */
    private ECKeyPair keyPair;
}
