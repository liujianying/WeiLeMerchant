package com.ydh.weile.entity;

/**
 * Created by liujianying on 14/10/21
 * 今日收款金额,今日卡券消费数
 */
public class NowDayConsumeInfoEntity {

//            "consumeAmount":"今日交易款",
//            "consumeNum":"今日交易卡券数",
//            "vcardNum":"储值卡消费数",
//            "cashCouponNum":"代金券消费数",
//            "couponNum":"优惠券消费数",
//            "mcardNum":"会员卡消费数",

    private String consumeAmount;
    private String consumeNum;
    private String vcardNum;
    private String cashCouponNum;
    private String couponNum;
    private String mcardNum;

    public String getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(String consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public String getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(String consumeNum) {
        this.consumeNum = consumeNum;
    }

    public String getVcardNum() {
        return vcardNum;
    }

    public void setVcardNum(String vcardNum) {
        this.vcardNum = vcardNum;
    }

    public String getCashCouponNum() {
        return cashCouponNum;
    }

    public void setCashCouponNum(String cashCouponNum) {
        this.cashCouponNum = cashCouponNum;
    }

    public String getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(String couponNum) {
        this.couponNum = couponNum;
    }

    public String getMcardNum() {
        return mcardNum;
    }

    public void setMcardNum(String mcardNum) {
        this.mcardNum = mcardNum;
    }
}
