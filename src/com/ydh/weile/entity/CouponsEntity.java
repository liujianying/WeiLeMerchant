package com.ydh.weile.entity;

/**
 * Created by liujianying on 14/10/23.
 * @优惠券
 */
public class CouponsEntity {

//    memberNick : 会员昵称,
//            "couponName":优惠券名称,
//            "couponNo":优惠券号,
//            "couponId":优惠券ID,
//            "couponAmount":优惠券面值,

    private String memberNick;
    private String couponName;
    private String couponNo;
    private String couponId;
    private String couponAmount;

    public String getMemberNick() {
        return memberNick;
    }

    public void setMemberNick(String memberNick) {
        this.memberNick = memberNick;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
    }
}
