package domain.learn4.domainService;

import domain.learn4.Asset;
import domain.learn4.WechatAccount;

public interface TransferService {
    /**
     * 转账功能
     * @param payer 支付方
     * @param payee 接收方
     * @param asset 资产
     */
    void transfer(WechatAccount payer, WechatAccount payee, Asset asset);
}
