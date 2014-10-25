package com.ydh.weile.entity;

public class MessageBoxComment {


	private String sourceId;//消费记录id
	private int cardType;//1：优惠劵2：代金卷3：会员卡4：储值卡"
	
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public int getCardType() {
		return cardType;
	}
	public void setCardType(int cardType) {
		this.cardType = cardType;
	}
}
