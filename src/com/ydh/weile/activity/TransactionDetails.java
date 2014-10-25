package com.ydh.weile.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.ydh.weile.android.BaseActivity;
import com.ydh.weile.merchant.R;

/**
 * Created by liujianying on 14-10-14.
 * @扫描结果
 */
public class TransactionDetails extends BaseActivity {


    private TableRow row0, row1, row2, row3, row4, row5, row6, row7;
    //row0
    private TextView member_name1;                               //会员昵称
    private TextView vouchers_name;                              //券的名称

    //row1
    private TextView vouchers_title;                             //优惠券标题
    private TextView vouchers_title_content;                     //优惠券标题内容
    //row2
    private TextView vouchers_type;                              //优惠券类型
    private TextView vouchers_type_name;                         //卡券类型
    //row3
    private TextView vouchers_number;                            //编号
    //row4
    private TextView number_consumption0;
    private EditText number_consumption1;
    private TextView number_consumption2;

    //row5
    private TextView period_validity0;
    private TextView period_validity1;

    //row6
    private TextView integral0;
    private TextView integral2;
    private EditText integral1;

    //row7
    private TextView transaction_serial_number0;
    private TextView transaction_serial_number1;

    private Button btn_confirmation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_details);

        initViews();
        initEvents();
    }

    @Override
    public void initViews() {

        setHeading(true, "扫描结果");
        row0 = (TableRow) findViewById(R.id.row0);
        row1 = (TableRow) findViewById(R.id.row1);
        row2 = (TableRow) findViewById(R.id.row2);
        row3 = (TableRow) findViewById(R.id.row3);
        row4 = (TableRow) findViewById(R.id.row4);
        row5 = (TableRow) findViewById(R.id.row5);
        row6 = (TableRow) findViewById(R.id.row6);
        row7 = (TableRow) findViewById(R.id.row7);

        member_name1 = (TextView) findViewById(R.id.member_name1);
        vouchers_name = (TextView) findViewById(R.id.vouchers_name);
        vouchers_title = (TextView) findViewById(R.id.vouchers_title);
        vouchers_title_content = (TextView) findViewById(R.id.vouchers_title_content);
        vouchers_type = (TextView) findViewById(R.id.vouchers_type);
        vouchers_type_name = (TextView) findViewById(R.id.vouchers_type_name);
        vouchers_number = (TextView) findViewById(R.id.vouchers_number);

        number_consumption0 = (TextView) findViewById(R.id.number_consumption0);
        number_consumption1 = (EditText) findViewById(R.id.number_consumption1);
        number_consumption2 = (TextView) findViewById(R.id.number_consumption2);

        period_validity0 = (TextView) findViewById(R.id.period_validity0);
        period_validity1 = (TextView) findViewById(R.id.period_validity1);

        integral0 = (TextView) findViewById(R.id.integral0);
        integral1 = (EditText) findViewById(R.id.integral1);
        integral2 = (TextView) findViewById(R.id.integral2);

        transaction_serial_number0 = (TextView) findViewById(R.id.transaction_serial_number0);
        transaction_serial_number1 = (TextView) findViewById(R.id.transaction_serial_number1);

        btn_confirmation = (Button) findViewById(R.id.btn_confirmation);
    }

    @Override
    public void initEvents() {

    }
}
