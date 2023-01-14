package cn.iocoder.yudao.module.blockchain.service.account;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 帐户服务类
 *
 * @author ruanzh.eth
 * @date 2023/01/14
 */
@Service
public class AccountService implements ApplicationContextAware {

    private final Map<String, AccountGeneratorInterface> map = new ConcurrentHashMap<>();


    /**
     * 生成账户
     *
     * @param chainTech 公链技术
     * @param mnemonic 助记词
     * @return {@link Account}
     */
    public Account generate(String chainTech, String mnemonic, Boolean owned) {
        AccountGeneratorInterface generator = Optional.ofNullable(map.get(chainTech)).orElseThrow(() -> new UnsupportedOperationException("未支持的公链"));
        return generator.execute(mnemonic, owned);
    }

    /**
     * 组装实现类
     *
     * @param applicationContext 应用程序上下文
     * @throws BeansException 异常
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, AccountGeneratorInterface> beans = applicationContext.getBeansOfType(AccountGeneratorInterface.class);
        beans.values().forEach(item -> map.put(item.getChainTech(), item));
    }
}
