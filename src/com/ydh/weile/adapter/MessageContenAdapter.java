package com.ydh.weile.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ydh.weile.entity.MessageContenEntity;
import com.ydh.weile.entity.MessageEntity;
import com.ydh.weile.merchant.R;
import com.ydh.weile.uitl.ViewHolderUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by liujianying on 14-10-13.
 */
public class MessageContenAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mInflater;
    private MessageEntity messageEntity;


    private static HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
    static {

        hashMap.put(0, " 系统消息");
        hashMap.put(1, " 消费消息");
        hashMap.put(2, " 交易消息");
    }


    public MessageContenAdapter(Context mContext, MessageEntity messageEntity) {
        this.mContext = mContext;
        this.messageEntity = messageEntity;
    }


    public void setMessageEntity(MessageEntity messageEntity) {
        this.messageEntity = messageEntity;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(messageEntity.getList() == null)return 0;
        return messageEntity.getList().size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if (mInflater == null) {
                mInflater = LayoutInflater.from(mContext);
            }
            convertView = LayoutInflater.from(mContext).inflate( R.layout.message_content_item, parent, false);
        }

        TextView msg_type = ViewHolderUtils.get(convertView, R.id.msg_type);
        TextView time = ViewHolderUtils.get(convertView, R.id.time);
        TextView msg_content = ViewHolderUtils.get(convertView, R.id.msg_content);
        MessageEntity.MessageConten msgEntity = messageEntity.getList().get(i);
        Drawable drawable = mContext.getResources().getDrawable(("0".equals(msgEntity.getIsRead())) ? R.drawable.icon_circle : R.drawable.icon_customize_circle_gray);
        drawable.setBounds(0, 0, 32, 32);
        msg_type.setCompoundDrawables(drawable, null, null, null);
        time.setText(msgEntity.getReceiveDate());
        msg_content.setText(msgEntity.getContent());
        msg_type.setText(hashMap.get(msgEntity.getMessageType()));
        time.setTag(msgEntity);
        return convertView;
    }



}
