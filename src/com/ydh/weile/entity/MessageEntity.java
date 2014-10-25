package com.ydh.weile.entity;


import java.io.Serializable;
import java.util.List;

/**
 * 未处理消息中心
 */
public class MessageEntity implements Serializable {

//    totalPage : 【Int】总页数,
//            "count":[int]总条数

    private int totalPage;
    private int count;
    private List<MessageConten> list;


    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MessageConten> getList() {
        return list;
    }

    public void setList(List<MessageConten> list) {
        this.list = list;
    }

    public class MessageConten implements Serializable {
//                "title":"消息标题
//                "content":"消息内容
//                "receiveDate":"接收时间
//                "isRead”:”是否已读",
//                "messageType":"消息类型 0系统消息,1消费消息2交易消息",
//                "turnRule":"跳转规则:0(卡券)|卡券类型(1会员卡2优惠券3代金券4储值卡)|卡IID",
//                “initiator”:”消息发起人,如果是为了用户显示手机号码”,

        /** 消息标题 */
        private String title;
        /** 消息内容 */
        private String content;

        /** 接收时间 */
        private String receiveDate;

        /** 是否已读 */
        private String isRead;

        /** 消息类型 0系统消息,1消费消息2交易消息 */
        private int messageType;

        /** 跳转规则:0(卡券)|卡券类型(1会员卡2优惠券3代金券4储值卡)|卡IID */
        private String turnRule;

        /** 消息发起人,如果是为了用户显示手机号码 */
        private String initiator;


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getReceiveDate() {
            return receiveDate;
        }

        public void setReceiveDate(String receiveDate) {
            this.receiveDate = receiveDate;
        }

        public String getIsRead() {
            return isRead;
        }

        public void setIsRead(String isRead) {
            this.isRead = isRead;
        }

        public int getMessageType() {
            return messageType;
        }

        public void setMessageType(int messageType) {
            this.messageType = messageType;
        }

        public String getTurnRule() {
            return turnRule;
        }

        public void setTurnRule(String turnRule) {
            this.turnRule = turnRule;
        }

        public String getInitiator() {
            return initiator;
        }

        public void setInitiator(String initiator) {
            this.initiator = initiator;
        }
    }

}
