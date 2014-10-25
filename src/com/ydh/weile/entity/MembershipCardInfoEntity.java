package com.ydh.weile.entity;

/**
 * Created by liujianying on 14/10/23.
 * @会员卡申请使用
 */
public class MembershipCardInfoEntity {

//            memberNick : 会员昵称,
//            "mcardName":会员卡名称,
//            "mcardNo":会员卡号,
//            "mcardId":会员卡ID,
//            "integralBalance":赠送积分,
//            "flowID":会员卡使用流水号,
//            “approvalState”:”审核状态（2：待审核，3：已审核）(待审核商家可以审核的,已审核则只是查看)”,
//            “approvalResult”:”审核结果（0：不通过，1：通过）通过=交易成功 不通过=交易失败”,
//            “approvalDesc”:” 审核说明（通过备注，不通过原因）”,
    /**会员昵称*/
    private String memberNick;
    /**会员卡名称*/
    private String mcardName;
    /**会员卡号*/
    private String mcardNo;
    /**会员卡ID*/
    private String mcardId;
    /**赠送积分*/
    private String integralBalance;
    /**会员卡使用流水号*/
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

    public String getMcardName() {
        return mcardName;
    }

    public void setMcardName(String mcardName) {
        this.mcardName = mcardName;
    }

    public String getMcardNo() {
        return mcardNo;
    }

    public void setMcardNo(String mcardNo) {
        this.mcardNo = mcardNo;
    }

    public String getMcardId() {
        return mcardId;
    }

    public void setMcardId(String mcardId) {
        this.mcardId = mcardId;
    }

    public String getIntegralBalance() {
        return integralBalance;
    }

    public void setIntegralBalance(String integralBalance) {
        this.integralBalance = integralBalance;
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
