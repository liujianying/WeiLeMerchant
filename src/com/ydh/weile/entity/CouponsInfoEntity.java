package com.ydh.weile.entity;

/**
 * Created by liujianying on 14/10/23.
 * @获取优惠券申请使用
 */
public class CouponsInfoEntity {
//
//    memberNick : 会员昵称,
//            "couponName":优惠券名称,
//            "couponNo":优惠券号,
//            "couponId":优惠券ID,
//            "couponAmount":优惠券面值,
//            "flowID":优惠券使用流水号
//    “approvalState”:”审核状态（2：待审核，3：已审核）(待审核商家可以审核的,已审核则只是查看)”,
//            “approvalResult”:”审核结果（0：不通过，1：通过）通过=交易成功 不通过=交易失败”,
//            “approvalDesc”:” 审核说明（通过备注，不通过原因）”,

    /**会员昵称*/
    private String memberNick;
    /**优惠券名称*/
    private String couponName;
    /**优惠券号*/
    private String couponNo;
    /**优惠券ID*/
    private String couponId;
    /**优惠券面值*/
    private String couponAmount;
    /**优惠券使用流水号*/
    private String flowID;
    /**审核状态（2：待审核，3：已审核）(待审核商家可以审核的,已审核则只是查看)*/
    private String approvalState;
    /**审核结果（0：不通过，1：通过）通过=交易成功 不通过=交易失败*/
    private String approvalResult;
    /**审核说明（通过备注，不通过原因*/
    private String approvalDesc;


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

    public String getFlowID() {
        return flowID;
    }

    public void setFlowID(String flowID) {
        this.flowID = flowID;
    }

    public String getApprovalState() {
        return approvalState;
    }

    public void setApprovalState(String approvalState) {
        this.approvalState = approvalState;
    }

    public String getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(String approvalResult) {
        this.approvalResult = approvalResult;
    }

    public String getApprovalDesc() {
        return approvalDesc;
    }

    public void setApprovalDesc(String approvalDesc) {
        this.approvalDesc = approvalDesc;
    }
}
