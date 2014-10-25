package com.ydh.weile.entity;

/**
 * Created by liujianying on 14/10/23.
 * 代金券实体
 */
public class VouchersEntity {

//    memberNick : 会员昵称,
//            "cashcouponName":代金券名称,
//            "cashcouponNo":代金券号,
//            "cashcouponId":代金券ID,
//            "cashcouponAmount":代金券面值,


    private String memberNick;
    private String cashcouponName;
    private String cashcouponNo;
    private String cashcouponId;
    private String cashcouponAmount;

    public String getMemberNick() {
        return memberNick;
    }

    public void setMemberNick(String memberNick) {
        this.memberNick = memberNick;
    }

    public String getCashcouponName() {
        return cashcouponName;
    }

    public void setCashcouponName(String cashcouponName) {
        this.cashcouponName = cashcouponName;
    }

    public String getCashcouponNo() {
        return cashcouponNo;
    }

    public void setCashcouponNo(String cashcouponNo) {
        this.cashcouponNo = cashcouponNo;
    }

    public String getCashcouponId() {
        return cashcouponId;
    }

    public void setCashcouponId(String cashcouponId) {
        this.cashcouponId = cashcouponId;
    }

    public String getCashcouponAmount() {
        return cashcouponAmount;
    }

    public void setCashcouponAmount(String cashcouponAmount) {
        this.cashcouponAmount = cashcouponAmount;
    }
}
