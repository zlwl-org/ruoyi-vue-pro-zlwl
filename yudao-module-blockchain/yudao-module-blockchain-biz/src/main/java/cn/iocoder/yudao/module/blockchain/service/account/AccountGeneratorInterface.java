package cn.iocoder.yudao.module.blockchain.service.account;

/**
 * 账户生成接口，每个公链技术对应一个生成实现类
 *
 * @author ruanzh.eth
 * @date 2023/01/14
 */
public interface AccountGeneratorInterface {

    /**
     * 公链技术
     *
     * @return {@link String}
     */
    String getChainTech();

    /**
     * 生成账户，返回地址，生成的地址为按照默认路径派生
     *
     * @param mnemonic 助记符（为空时，生成随机地址）
     * @return {@link String}
     */
    Account generate(String mnemonic);

    /**
     * 保存账户
     *
     * @param account 账户
     * @param owned   是否被使用
     */
    void save(Account account, Boolean owned);

    default Account execute(String mnemonic, Boolean owned) {
        Account account = generate(mnemonic);
        save(account, owned);
        return account;
    }
}
