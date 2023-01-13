package cn.iocoder.yudao.module.blockchain.util;

import com.google.common.collect.ImmutableList;
import org.bitcoinj.crypto.*;
import org.jetbrains.annotations.NotNull;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.MnemonicUtils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import static cn.iocoder.yudao.module.blockchain.enums.CoinType.*;


/**
 * @author ruanzh.eth
 */
public class MnemonicUtil {

    public static final int DEFAULT_ADDRESS_INDEX = 0;
    public static final ImmutableList<Integer> MNEMONIC_WORDS_COUNT = ImmutableList.of(12, 15, 18, 24);
    public static final String DEFAULT_PASSWORD = "";


    private MnemonicUtil() {
    }

    /**
     * 生成派生路径
     * BIP44提出5层的路径: m/purpose'/coin_type'/account'/change/address_index
     * 最后一层路径 address_index 本工具在派生子密钥时使用
     * see {@link #deriveChildKey(DeterministicKey, ImmutableList, int)}
     *
     * @param purpose  目
     * @param coinType 硬币
     * @param account  账户
     * @param change   改变
     * @return {@link ImmutableList}<{@link ChildNumber}>
     */
    public static ImmutableList<ChildNumber> generateDerivationPath(ChildNumber purpose, ChildNumber coinType, ChildNumber account, ChildNumber change) {
        return ImmutableList.of(purpose, coinType, account, change);
    }


    /**
     * 生成派生路径
     *
     * @param purpose  目
     * @param coinType 硬币类型
     * @param account  账户
     * @param change   改变
     * @return {@link ImmutableList}<{@link ChildNumber}>
     */
    public static ImmutableList<ChildNumber> generateDerivationPath(int purpose, int coinType, int account, int change) {
        return generateDerivationPath(hardenedChildNumber(purpose), hardenedChildNumber(coinType), hardenedChildNumber(account), new ChildNumber(change));
    }

    /**
     * 硬币默认路径
     *
     * @param coin 硬币
     * @return {@link ImmutableList}<{@link ChildNumber}>
     */
    public static ImmutableList<ChildNumber> coinDefaultPath(String coin) {
        ImmutableList<ChildNumber> path;
        switch (coin) {
            case eth_string -> path = generateDefaultDerivationPath(eth);
            case trx_string -> path = generateDefaultDerivationPath(trx);
            default -> throw new IllegalArgumentException("Invalid coin type: " + coin);
        }
        return path;
    }

    @NotNull
    private static ImmutableList<ChildNumber> generateDefaultDerivationPath(int coinType) {
        return generateDerivationPath(BIP44, coinType, 0, 0);
    }

    @NotNull
    private static ChildNumber hardenedChildNumber(int purpose) {
        return new ChildNumber(purpose, true);
    }

    /**
     * 随机助记词
     *
     * @return {@link String}
     */
    public static String randomMnemonic() {
        byte[] initialEntropy = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(initialEntropy);
        return MnemonicUtils.generateMnemonic(initialEntropy);
    }

    /**
     * 助记词转换种子
     *
     * @param mnemonic 助记词
     * @param password 密码
     * @return {@link byte[]}
     */
    public static byte[] toSeed(String mnemonic, String password) {
        List<String> words = Arrays.stream(mnemonic.split(" ")).toList();
        return MnemonicCode.toSeed(words, password);
    }

    /**
     * 通过种子生成根密钥
     *
     * @param seed 种子
     * @return {@link DeterministicKey}
     */
    public static DeterministicKey generateMasterKey(byte[] seed) {
        return HDKeyDerivation.createMasterPrivateKey(seed);
    }

    /**
     * 通过根密钥生成子密钥
     *
     * @param masterPrivateKey 根密钥
     * @param path             路径
     * @param addressIndex     地址索引
     * @return {@link DeterministicKey}
     */
    public static DeterministicKey deriveChildKey(DeterministicKey masterPrivateKey, ImmutableList<ChildNumber> path, int addressIndex) {
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(masterPrivateKey);
        return deterministicHierarchy.deriveChild(path, false, true, new ChildNumber(addressIndex));
    }

    /**
     * 使用助记词生成密钥对
     *
     * @param mnemonic     助记词
     * @param password     密码
     * @param path         路径
     * @param addressIndex 地址索引
     * @return {@link ECKeyPair}
     */
    public static ECKeyPair createFromMnemonic(String mnemonic, String password, ImmutableList<ChildNumber> path, int addressIndex) {
        /* 1. 用助记词生成种子 */
        byte[] seed = toSeed(mnemonic, password);
        /* 2. 用种子生成根密钥 */
        DeterministicKey masterKey = generateMasterKey(seed);
        /* 3. 用根密钥派生子密钥 */
        DeterministicKey childKey = deriveChildKey(masterKey, path, addressIndex);

        return ECKeyPair.create(childKey.getPrivKey());
    }

    /**
     * 使用助记词创建默认账户密钥对
     *
     * @param mnemonic 助记词
     * @param coin     硬币
     * @return {@link ECKeyPair}
     */
    public static ECKeyPair createDefaultKeyFromMnemonic(String mnemonic, String coin) {
        ImmutableList<ChildNumber> defaultPath = coinDefaultPath(coin);
        return createFromMnemonic(mnemonic, DEFAULT_PASSWORD, defaultPath, DEFAULT_ADDRESS_INDEX);
    }

    public static String getDefaultAddressFromMnemonic(String mnemonic, String coin) {
        String address = null;
        ECKeyPair keyPair = createDefaultKeyFromMnemonic(mnemonic, coin);
        switch (coin) {
            case trx_string -> address = TronAddressUtil.publicKeyToBase58(keyPair.getPublicKey());
            case eth_string -> address = "0x" + Keys.getAddress(keyPair);
            case btc_string -> address = "还没实现";
        }
        return address;
    }

}
