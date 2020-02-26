package com.kaoyaya.tongkai.entity;

public class JdResponse {


    /**
     * extraMsg : {"tradeNum":"de3dd8b03fb752718c6c53339e562d5d27cac506af7d7fd2","amount":"b68e18b646fb73e4","currency":"a22a9bfec2e5c3a7","tradeTime":"bd069216e5af33814f06ef113b7209a375fb7fa370545720","status":"7f56482b4727cc68","sign":"E4MZgUSPkETxoxp6SEd0qTsDBbLny0rHSVaIh2BPLDugR0rahoeAJLQz0XmwA8GE8xyIVzVXBoRvWzWiFCvudf6dmL7q7yxyoZ4HLkSROMp9adHBNY87HA76mZHqGXrz7BYMD/Y13p7/6LZRocTxgMxiaSY7O/TykR01rVwTRwk\u003d"}
     * needSuccessPage : false
     * payStatus : JDP_PAY_SUCCESS
     * payType :
     * payWayInfoList : []
     */

    private String extraMsg;
    private boolean needSuccessPage;
    private String payStatus;
    private String payType;

    private ExtraMsg msg;


    public ExtraMsg getMsg() {
        return msg;
    }

    public void setMsg(ExtraMsg msg) {
        this.msg = msg;
    }

    public void setExtraMsg(String extraMsg) {
        this.extraMsg = extraMsg;
    }

    public boolean isNeedSuccessPage() {
        return needSuccessPage;
    }

    public void setNeedSuccessPage(boolean needSuccessPage) {
        this.needSuccessPage = needSuccessPage;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getExtraMsg() {
        return extraMsg;
    }



}
