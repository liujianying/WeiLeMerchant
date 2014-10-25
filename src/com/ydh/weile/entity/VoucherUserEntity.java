package com.ydh.weile.entity;

/**
 * Created by liujianying on 14/10/23.
 * @代金券申请使用
 */
public class VoucherUserEntity {

//            "memberNick" : 会员昵称,
//            "cashcouponName":代金券名称,
//            "cashcouponNo":代金券号,
//            "cashcouponId":代金券ID,
//            "cashcouponAmount":代金券面值,
//            "flowID":代金券使用流水号,
//            “approvalState”:”审核状态（2：待审核，3：已审核）(待审核商家可以审核的,已审核则只是查看)”,
//            “approvalResult”:”审核结果（0：不通过，1：通过）通过=交易成功 不通过=交易失败”,
//            “approvalDesc”:” 审核说明（通过备注，不通过原因）”,



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
    /**代金券名称*/
    private String cashcouponName;
    /**代金券号*/
    private String cashcouponNo;
    /**代金券ID*/
    private String cashcouponId;
    /**代金券面值*/
    private String cashcouponAmount;
    /**代金券使用流水号*/
    private String flowID;
    /**审核状态（2：待审核，3：已审核）(待审核商家可以审核的,已审核则只是查看)*/
    private String approvalState;
    /**审核结果（0：不通过，1：通过）通过=交易成功 不通过=交易失败*/
    private String approvalResult;
    /**审核说明（通过备注，不通过原因）*/
    private String approvalDesc;

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
