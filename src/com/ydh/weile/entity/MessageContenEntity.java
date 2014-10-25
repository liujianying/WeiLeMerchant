package com.ydh.weile.entity;

import java.io.Serializable;

/**
 * Created by liujianying on 14-10-13.
 * @消息中心实体类
 */
public class MessageContenEntity implements Serializable{

    public boolean isReadFlag = false;
    private int message_type;
    private long time;
    private String msg_content;

    public int getMessage_type() {
        return message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

}
