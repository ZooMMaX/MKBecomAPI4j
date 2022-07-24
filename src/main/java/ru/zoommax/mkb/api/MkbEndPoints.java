package ru.zoommax.mkb.api;

public enum MkbEndPoints {
    pareq3dsACS ("/eCom_api/finOperate/acs/paReq"),
    pareq3dsFiscalACS ("/eCom_api/finOperate/acs/paReq/fiscalReceipt"),
    pares3dsACS ("/eCom_api/finOperate/acs/paRes"),
    purchase ("/eCom_api/finOperate/purchase"),
    purchaseFiscal ("/eCom_api/purchase/fiscalReceipt"),
    samsungPurchase ("/eCom_api/finOperate/SamsungToken/purchase"),
    applePurchase ("/eCom_api/finOperate/AppleToken/purchase"),
    purchaseWithout3dsACS ("/eCom_api/finOperate/purchase/without3DS"),
    pareq3dsC2A ("/eCom_api/finOperate/c2a/paReq"),
    pares3dsC2A ("/eCom_api/finOperate/c2a/paRes"),
    purchaseC2A ("/eCom_api/finOperate/c2a/purchase"),
    purchaseA2C ("/eCom_api/finOperate/a2c/purchase"),
    recurring ("/eCom_api/finOperate/recurring"),
    refund ("/eCom_api/finoperate/refund"),
    revers ("/eCom_api/finoperate/revers"),
    capture ("/eCom_api/finoperate/capture"),
    client ("/eCom_api/client_identification/client/{mid}/{client_id}"),
    clientCardDelete ("/eCom_api/client_identification/card/{mid}/{client_id}/{card_id}"),
    clientCardAllDelete ("/eCom_api/client_identification/card/{mid}/{client_id}"),
    getOrderStatus ("/eCom_api/finOperate/getOrderStatus/{mid}/{oid}");




    private final String name;

    private MkbEndPoints(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
