package cn.iocoder.yudao.module.blockchain.util;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * TRON网络账户地址有两种格式，一种是Hex格式，另一种是Base58格式。
 * Hex格式:用公钥P作为输入，计算SHA3得到结果H，取H的最后20字节，在前面填充一个字节0x41得到Hex格式地址。
 * Base58格式:对hex格式的地址进行basecheck计算得到Base58格式地址，所有账户地址的第一个字符均为T。
 *
 * @author ruanzh.eth
 */
public class TronAddressUtil {

    private TronAddressUtil() {
    }

    public static BigInteger publicKeyToNumber(BigInteger publicKey) {
        return Numeric.toBigInt(Keys.getAddress(publicKey));
    }

    public static String publicKeyToBase58(BigInteger publicKey) {
        BigInteger number = publicKeyToNumber(publicKey);
        return numberToBase58(number);
    }

    public static BigInteger privateKeyToNumber(BigInteger privateKey) {
        return Numeric.toBigInt(Keys.getAddress(ECKeyPair.create(privateKey)));
    }

    public static String numberToHex(BigInteger number) {
        return "41" + number.toString(16);
    }

    public static BigInteger hexToNumber(String hex) {
        return Numeric.toBigInt(hex.substring(2));
    }

    public static String numberToBase58(BigInteger number) {
        String hex = numberToHex(number);
        return hexToBase58(hex);
    }

    public static BigInteger base58ToNumber(String base58) {
        byte[] rawValue = Base58Check.base58ToBytes(base58);
        return Numeric.toBigInt(Arrays.copyOfRange(rawValue, 1, 21));
    }

    public static String hexToBase58(String hex) {
        byte[] raw = Numeric.toBytesPadded(hexToNumber(hex), 21);
        raw[0] = 0x41;
        return Base58Check.bytesToBase58(raw);
    }

    public static String base58ToHex(String base58) {
        BigInteger number = base58ToNumber(base58);
        return numberToHex(number);
    }

}
