package cn.iocoder.yudao.module.blockchain.job;

import cn.iocoder.yudao.framework.quartz.core.handler.JobHandler;
import cn.iocoder.yudao.module.blockchain.service.account.AccountService;
import cn.iocoder.yudao.module.blockchain.service.eth.EthAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static cn.iocoder.yudao.module.blockchain.enums.CoinType.eth_string;

@Component
@Slf4j
public class EthAccountGenerateJob implements JobHandler {

    @Resource
    private AccountService accountService;
    @Resource
    private EthAccountService ethAccountService;

    @Override
    public String execute(String param) throws Exception {
        log.info("EthAccountGenerateJob start");
        for (int i = 0; i < 60; i++) {
            accountService.generate(eth_string, null, false);
        }
        log.info("EthAccountGenerateJob ended");
        return "创建60个地址";
    }
}
