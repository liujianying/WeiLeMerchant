package com.ydh.weile.entity;

/**
 * Created by liujianying on 14/10/23.
 *
 * @获取储值卡申请支付
 */
public class StorageCardInfo {

//    "memberNick":会员昵称,
//            "vcardName":储值卡名称,
//            "vcardNo":储值卡号,
//            "vcardId":储值卡ID,
//            "vcardAmount":储值卡面值,
//            "consumerAmount":储值卡消费金额,
//            "vcardType":1：次数卡，2：金额卡,
//            "residueVcardAmount":剩余储值卡面值,
//            "integralBalance":赠送积分,
//            "flowID":会员卡使用流水号,
//            “approvalState”:”审核状态（2：待审核，3：已审核）(待审核商家可以审核的,已审核则只是查看)”,
//            “approvalResult”:”审核结果（0：不通过，1：通过）通过=交易成功 不通过=交易失败”,
//            “approvalDesc”:” 审核说明（通过备注，不通过原因）”,
    /**会员昵称*/
    private String memberNick;
    /**储值卡名称*/
    private String vcardName;
    /**储值卡号*/
    private String vcardNo;
    /**储值卡ID*/
    private String vcardId;
    /**储值卡面值*/
    private String vcardAmount;
    /**储值卡消费金额*/
    private String consumerAmount;
    /**1：次数卡，2：金额卡*/
    private String vcardType;
    /**剩余储值卡面值*/
    private String residueVcardAmount;
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

    public String getVcardName() {
        return vcardName;
    }

    public void setVcardName(String vcardName) {
        this.vcardName = vcardName;
    }

    public String getVcardNo() {
        return vcardNo;
    }

    public void setVcardNo(String vcardNo) {
        this.vcardNo = vcardNo;
    }

    public String getVcardId() {
        return vcardId;
    }

    public void setVcardId(String vcardId) {
        this.vcardId = vcardId;
    }

    public String getVcardAmount() {
        return vcardAmount;
    }

    public void setVcardAmount(String vcardAmount) {
        this.vcardAmount = vcardAmount;
    }

    public String getConsumerAmount() {
        return consumerAmount;
    }

    public void setConsumerAmount(String consumerAmount) {
        this.consumerAmount = consumerAmount;
    }

    public String getVcardType() {
        return vcardType;
    }

    public void setVcardType(String vcardType) {
        this.vcardType = vcardType;
    }

    public String getResidueVcardAmount() {
        return residueVcardAmount;
    }

    public void setResidueVcardAmount(String residueVcardAmount) {
        this.residueVcardAmount = residueVcardAmount;
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
