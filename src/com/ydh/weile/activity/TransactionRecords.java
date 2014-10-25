package com.ydh.weile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.fastjson.JSON;
import com.ydh.weile.adapter.MessageContenAdapter;
import com.ydh.weile.adapter.TransactionRecordsAdapter;
import com.ydh.weile.android.BaseActivity;
import com.ydh.weile.entity.MessageContenEntity;
import com.ydh.weile.entity.TransactionEntity;
import com.ydh.weile.merchant.R;
import com.ydh.weile.uitl.LogUitl;
import com.ydh.weile.view.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujianying on 14-10-11.
 * @交易记录
 */
public class TransactionRecords extends BaseActivity implements XListView.IXListViewListener {



    private XListView mListView;

    private TransactionRecordsAdapter msgAdapter;
    private List<TransactionEntity> items = new ArrayList<TransactionEntity>();
    private Handler mHandler;
    private int start = 0;
    private static int refreshCnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_records);
        initViews();
        initEvents();
    }



    @Override
    public void initViews() {
        setHeading(true, "交易记录");
        mListView = (XListView) findViewById(R.id.xListView);
        mListView.setPullLoadEnable(true);
        geneItems();
        msgAdapter = new TransactionRecordsAdapter(this,items);
        mListView.setAdapter(msgAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(TransactionRecords.this, ConsumptionDetails.class);
                intent.putExtra("MessageContenAdapter", items.get(i-1));

                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                items.get(i-1).isReadFlag = false;
//                msgAdapter.notifyDataSetChanged();
            }
        });
//		mListView.setPullLoadEnable(false);
//		mListView.setPullRefreshEnable(false);
        mListView.setXListViewListener(this);
        mHandler = new Handler();
    }

    @Override
    public void initEvents() {

    }


    private void geneItems() {

//        private String ico;                         //会员头像
//        private String name;                        //会员名称
//        private String iphoneNumebr;                //电话号码
//        private String time;                        //交易时间
//        private int tradingType;                    //交易类别
//        private String transactionAmount;           //交易金额

        for (int i = 0; i != 20; ++i) {
            TransactionEntity msg = new TransactionEntity();
            msg.setIco("http://c.hiphotos.baidu.com/image/w%3D230/sign=3c2cf79bd309b3deebbfe36bfcbe6cd3/d788d43f8794a4c2177cb7d80cf41bd5ac6e39e1.jpg");
            msg.setName("勇哥哥");
            msg.setIphoneNumebr("18809876800");
            msg.setTime(System.currentTimeMillis() + "");
            msg.setTradingType(i%2);
            msg.setTransactionAmount("" + i);

//          ms

            items.add(msg);
        }

        LogUitl.SystemOut(JSON.toJSONString(items));
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                start = ++refreshCnt;
                items.clear();
                geneItems();
                msgAdapter = new TransactionRecordsAdapter(TransactionRecords.this,items);
                mListView.setAdapter(msgAdapter);
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                geneItems();
                msgAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }
}
