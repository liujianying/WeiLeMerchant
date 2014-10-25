package com.ydh.weile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.squareup.picasso.Picasso;
import com.ydh.weile.adapter.MessageContenAdapter;
import com.ydh.weile.android.BaseActivity;
import com.ydh.weile.entity.MessageContenEntity;
import com.ydh.weile.entity.MessageEntity;
import com.ydh.weile.interfaces.NetCode;
import com.ydh.weile.merchant.R;
import com.ydh.weile.net.mode.uitl.MessageContentMode;
import com.ydh.weile.system.config.NetExceptionUitl;
import com.ydh.weile.uitl.LogUitl;
import com.ydh.weile.view.LoadDataView;
import com.ydh.weile.view.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujianying on 14-10-10.
 * @消息中心
 */
public class MessageCenter extends BaseActivity implements XListView.IXListViewListener {


    private LoadDataView loadDataView;
    private XListView mListView;

    private MessageContenAdapter msgAdapter;

    private MessageEntity messageEntity = new MessageEntity();
    private int pageCount = 20;
    private int crrentIndex = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_content);
        initViews();
        initEvents();
    }

    @Override
    public void initViews() {
        setHeading(true, "消息中心");

        mListView = (XListView) findViewById(R.id.xListView);
        View emptyView = findViewById(R.id.rl_layout);
//        ImageView img = (ImageView) emptyView.findViewById(R.id.img_id);
//        TextView ev = (TextView) emptyView.findViewById(R.id.tv_list_view_content);
//        ev.setText("暂无待付款订单");
//        img.setImageResource(R.drawable.icon_pending_payment_waiting_dedault);
        mListView.setEmptyView(emptyView);
        loadDataView = (LoadDataView) emptyView.findViewById(R.id.loadDataView);
        loadDataView.setLoadSucessView(null);
        loadDataView.show();

        mListView.setPullLoadEnable(true);
        msgAdapter = new MessageContenAdapter(this,messageEntity);
        mListView.setAdapter(msgAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if(messageEntity.getList() != null && messageEntity.getList().size() >= 1) {

                    MessageEntity.MessageConten msg = messageEntity.getList().get(i - 1);
                    try {
                        String [] str = msg.getTurnRule().split("\\|");
                        if("0".equals(str[0])) {

                            Intent intent = new Intent(MessageCenter.this, ConsumptionDetails.class);
                            intent.putExtra("MessageConten",msg);
                            startActivity(intent);
                            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }


                }

            }
        });
//		mListView.setPullLoadEnable(false);
//		mListView.setPullRefreshEnable(false);
        mListView.setXListViewListener(this);
    }

    @Override
    public void initEvents() {

        MessageContentMode.newMessageContentMode().appUntreatedMessage(mContext, pageCount, crrentIndex, handler);

    }



    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    @Override
    public void onRefresh() {
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                start = ++refreshCnt;
//                items.clear();
//                geneItems();
//                msgAdapter = new MessageContenAdapter(MessageCenter.this,items);
//                mListView.setAdapter(msgAdapter);
//                onLoad();
//            }
//        }, 2000)
//
// ;

        crrentIndex = 1;
        MessageContentMode.newMessageContentMode().appUntreatedMessage(mContext, pageCount, crrentIndex, handler);

//        MessageContentMode.newMessageContentMode().appUntreatedMessage(mContext, pageCount, crrentIndex, handler);
    }

    @Override
    public void onLoadMore() {
        MessageContentMode.newMessageContentMode().appUntreatedMessage(mContext, pageCount, crrentIndex, handler);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            onLoad();
            switch (msg.what) {

                case NetCode.RequestSuccess:
                    loadDataView.closed(LoadDataView.LoadResponse.Sucess);
                    messageEntity = (MessageEntity) msg.obj;
                    msgAdapter.setMessageEntity(messageEntity);
                    if(messageEntity.getTotalPage() == 1 || messageEntity.getList().size() <20) {
                        mListView.setPullLoadEnable(false);
                    } else {
                        crrentIndex++;
                    }
                    break;
                case NetCode.RequestFailed:
                    NetExceptionUitl.newNetExceptionUitl().showExceptionToast(mContext, (Integer) msg.obj, loadDataView);
                    break;
                case NetCode.System_Error:
                    loadDataView.closed(LoadDataView.LoadResponse.Fail);
                    break;
                default:
                    break;
            }
        }
    };
}
