package com.ydh.weile.entity;

import java.util.List;

/**
 * Created by liujianying on 14/10/23.
 * @交易记录
 */
public class CardDealResultEntity {

//    totalPage : 【Int】总页数,
//            "count":[int]总条数
//    "list":{
//          "flowId”:”流水ID",
//          “cardType”:”[int] 卡券类型 0全部,1储值卡2代金券3会员卡4优惠券”
//          “memberNick”:会员昵称,
//          "approvalDate":"审核时间
//          "amount”:”交易金额",
//          “approvalResult”:”审核结果（0：不通过，1：通过）通过=交易成功 不通过=交易失败”,
//         }

    private String totalPage;
    private String count;
    private List<TransactionRecords> list;

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<TransactionRecords> getList() {
        return list;
    }

    public void setList(List<TransactionRecords> list) {
        this.list = list;
    }

    public class TransactionRecords {
        /**流水ID*/
        private String flowId;
        /**卡券类型 0全部,1储值卡2代金券3会员卡4优惠券*/
        private int cardType;
        /**会员昵称*/
        private String memberNick;
        /**审核时间*/
        private String approvalDate;
        /**交易金额*/
        private String amount;
        /**审核结果（0：不通过，1：通过）通过=交易成功 不通过=交易失败*/
        private String approvalResult;

        public String getFlowId() {
            return flowId;
        }

        public void setFlowId(String flowId) {
            this.flowId = flowId;
        }

        public int getCardType() {
            return cardType;
        }

        public void setCardType(int cardType) {
            this.cardType = cardType;
        }

        public String getMemberNick() {
            return memberNick;
        }

        public void setMemberNick(String memberNick) {
            this.memberNick = memberNick;
        }

        public String getApprovalDate() {
            return approvalDate;
        }

        public void setApprovalDate(String approvalDate) {
            this.approvalDate = approvalDate;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getApprovalResult() {
            return approvalResult;
        }

        public void setApprovalResult(String approvalResult) {
            this.approvalResult = approvalResult;
        }
    }

}
