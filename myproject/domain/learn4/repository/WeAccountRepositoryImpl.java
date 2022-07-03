package domain.learn4.repository;

import domain.learn4.WechatAccount;

/**
 * 这里用了po 因为entity不参与数据表的映射关系
 * 问题：account转po 我们并不需要所有属性 我们关注哪些属性被修改了是需要入库的，哪些
 * 不需要 这就带来change tracking问题
 */
public class WeAccountRepositoryImpl implements AccountRepository {
    @Override
    public void save(WechatAccount wechatAccount) {
        /*WechatAccountPO wechatAccountPO = WechatAccountConverter.convert(wechatAccount);
        wechatAccountDAO.save(wechatAccountPO);

        Wallet wallet = WalletFactory.obtain(wechatAccount);
        WalletPO walletPO = walletConverter.convert(wallet);
        walletDAO.save(walletPO);

        Balance balance = BalanceFactory.obtain(wallet);
        BalancePO balancePO = balanceConverter.convert(balance);
        balanceDAO.save(balancePO);

        Statement statement = StatementFactory.obtain(wallet);
        StatementPO statementPO = statementConverter.convert(statement);
        statementDAO.save(statementPO);

        //... 其他WechatAccount的属性*/
    }
}
