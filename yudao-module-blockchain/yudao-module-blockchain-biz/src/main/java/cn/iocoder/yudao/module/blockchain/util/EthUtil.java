package cn.iocoder.yudao.module.blockchain.util;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * ETH工具类
 *
 * @author ruanzh.eth
 * @date 2023/01/14
 */
public class EthUtil {

//    private EthUtil() {
//    }

    /**
     * 获取ETH余额
     *
     * @param web3j web3j
     * @param address 地址
     * @param precision 精度
     * @return {@link BigDecimal}
     */
    public static BigDecimal getEth(Web3j web3j, String address, int precision) throws IOException {
        BigInteger balance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send().getBalance();
        return new BigDecimal(balance).divide(BigDecimal.TEN.pow(precision));

    }
}
