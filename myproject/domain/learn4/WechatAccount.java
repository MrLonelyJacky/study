package domain.learn4;

/**
 * 微信账户，是聚合根，唯一标识是手机号
 */
public class WechatAccount implements AggregateRoot<PhoneNumber>{
    private PhoneNumber id;
    private Wallet wallet;
    //...
    private WechatAccount() {}

    public void withdraw(Asset asset) {
        wallet.pay(asset);
    }

    public void deposit(Asset asset) {
        wallet.receive(asset);
    }
}
