package com.ydh.weile.entity;

/**
 * Created by liujianying on 14/10/23.
 * @储值卡
 */
public class StorageCard {


//             memberNick : 会员昵称,
//            "vcardName":储值卡名称,
//            "vcardNo":储值卡号,
//            "vcardId":储值卡ID,
//            "vcardAmount":储值卡面值,
//            "vcardType":1：次数卡，2：金额卡,
//            "residueVcardAmount":剩余储值卡面值,

    private String memberNick;
    private String vcardName;
    private String vcardNo;
    private String vcardId;
    private String vcardAmount;
    private String vcardType;
    private String residueVcardAmount;


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
}
