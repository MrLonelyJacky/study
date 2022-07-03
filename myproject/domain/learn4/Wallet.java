package domain.learn4;

public class Wallet implements Entity<WalletNumber>{
    private WalletNumber id;
    private Balance balance; // 余额
    //...
    private Wallet() {}

    public void pay(Asset asset) {
        /*balance.decrease(asset.getBalance);
        statement.add(asset);*/
    }

    public void receive(Asset asset) {
        /*balance.increase(asset.getBalance);
        statement.add(asset);*/
    }
}
