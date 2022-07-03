package domain.learn4.domainService;

import domain.learn4.Asset;
import domain.learn4.WechatAccount;
import domain.learn4.repository.AccountRepository;

/**
 * domain service
 * 用于封装多Entity或者跨业务域的问题，比如注册逻辑里的风控奖励等逻辑
 * 常常伴随状态的改变 进行入库
 */
public class TransferServiceImpl implements TransferService{

    public AccountRepository accountRepository;


    @Override
    public void transfer(WechatAccount payer, WechatAccount payee, Asset asset) {
        payer.withdraw(asset);
        payee.deposit(asset);
        //伴随着状态的改变
        accountRepository.save(payee);
        accountRepository.save(payer);
    }
}
