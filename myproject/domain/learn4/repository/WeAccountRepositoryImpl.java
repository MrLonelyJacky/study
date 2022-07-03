package domain.learn4.repository;

import domain.learn4.WechatAccount;

public class WeAccountRepositoryImpl implements AccountRepository{
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
