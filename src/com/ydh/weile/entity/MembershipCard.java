package com.ydh.weile.entity;

/**
 * Created by liujianying on 14/10/23.
 * @获取会员卡详情
 */
public class MembershipCard {

//            memberNick : 会员昵称,
//            "mcardName":会员卡名称,
//            "mcardNo":会员卡号,
//            "mcardId":会员卡ID,

    private String memberNick;
    private String mcardName;
    private String mcardNo;
    private String mcardId;


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
}
