package com.ydh.weile.entity;

import java.io.Serializable;

/**
 * Created by liujianying on 14-10-13.
 * @交易记录实体类
 */
public class TransactionEntity implements Serializable{

    private String ico;                         //会员头像
    private String name;                        //会员名称
    private String iphoneNumebr;                //电话号码
    private String time;                        //交易时间
    private int tradingType;                    //交易类别
    private String transactionAmount;           //交易金额


    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIphoneNumebr() {
        return iphoneNumebr;
    }

    public void setIphoneNumebr(String iphoneNumebr) {
        this.iphoneNumebr = iphoneNumebr;
    }

    public int getTradingType() {
        return tradingType;
    }

    public void setTradingType(int tradingType) {
        this.tradingType = tradingType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
