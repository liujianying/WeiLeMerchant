package com.ydh.weile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ydh.weile.entity.TransactionEntity;
import com.ydh.weile.merchant.R;
import com.ydh.weile.uitl.ViewHolderUtils;

import java.util.List;

/**
 * Created by liujianying on 14-10-13.
 */
public class TransactionRecordsAdapter extends BaseAdapter {


    private Context mContext;
    private List<TransactionEntity> list;
    private LayoutInflater mInflater;

    public TransactionRecordsAdapter(Context mContext, List<TransactionEntity> list) {

        this.mContext = mContext;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
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
            convertView = LayoutInflater.from(mContext).inflate( R.layout.transaction_records_item, parent, false);
        }

        ImageView ico = ViewHolderUtils.get(convertView, R.id.transaction_ico);
        TextView name = ViewHolderUtils.get(convertView, R.id.name);
        TextView money = ViewHolderUtils.get(convertView, R.id.money);
        TextView time = ViewHolderUtils.get(convertView, R.id.time);
        TextView transaction_type = ViewHolderUtils.get(convertView, R.id.transaction_type);


        TransactionEntity msgEntity = list.get(i);
        Picasso.with(mContext).load(msgEntity.getIco()).resize(100,100).placeholder(R.drawable.non_pic_defaults).into(ico);
        time.setText(msgEntity.getTime());
        name.setText(msgEntity.getName());
        money.setText(msgEntity.getTransactionAmount());
        transaction_type.setText(msgEntity.getTradingType() + "");

//        http://a.hiphotos.baidu.com/image/w%3D230/sign=cc580ad036a85edffa8cf920795509d8/cc11728b4710b912b895957ec1fdfc03934522dd.jpg

//        TextView msg_type = ViewHolderUtils.get(convertView, R.id.msg_type);
//        TextView time = ViewHolderUtils.get(convertView, R.id.time);
//        TextView msg_content = ViewHolderUtils.get(convertView, R.id.msg_content);
//        MessageContenEntity msgEntity = list.get(i);
//        Drawable drawable = mContext.getResources().getDrawable(msgEntity.isReadFlag ?   R.drawable.icon_circle : R.drawable.icon_customize_circle_gray);
//        drawable.setBounds(0, 0, 32, 32);
//        msg_type.setCompoundDrawables(drawable, null, null, null);
//        time.setText(msgEntity.getTime() + "");
//        msg_content.setText(msgEntity.getMsg_content());
//        msg_type.setText(hashMap.get(msgEntity.getMessage_type()));
//        time.setTag(msgEntity);

        return convertView;
    }
}
