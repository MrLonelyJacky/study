package domain.learn4.repository;

import domain.learn4.PhoneNumber;
import domain.learn4.WechatAccount;

public interface AccountRepository extends Repository<WechatAccount, PhoneNumber>{
    @Override
    void save(WechatAccount wechatAccount);
}
