package com.ydh.weile.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.ydh.weile.android.BaseActivity;
import com.ydh.weile.entity.CouponsInfoEntity;
import com.ydh.weile.entity.MembershipCardInfoEntity;
import com.ydh.weile.entity.MessageEntity;
import com.ydh.weile.entity.StorageCardInfo;
import com.ydh.weile.entity.VoucherUserEntity;
import com.ydh.weile.interfaces.NetCode;
import com.ydh.weile.merchant.R;
import com.ydh.weile.net.mode.uitl.MessageContentMode;
import com.ydh.weile.system.config.NetExceptionUitl;
import com.ydh.weile.uitl.SafetyUitl;
import com.ydh.weile.view.LoadDataView;

/**
 * Created by liujianying on 14-10-13.
 * @消费详情
 */
public class ConsumptionDetails extends BaseActivity implements View.OnClickListener{

    private final int couponsFlag     = 1;                                 //优惠券
    private final int vouchersFlag    = 2;                                 //代金券
    private final int membershipCard  = 3;                                 //会员卡
    private final int storageCardFlag = 4;                                 //储值卡


    private StorageCardInfo                   storageCardInfo;       //储值卡
    private CouponsInfoEntity               couponsInfoEntity;       //优惠券entity
    private VoucherUserEntity               voucherUserEntity;       //代金券entity
    private MembershipCardInfoEntity membershipCardInfoEntity;       //会员卡entity

    private TextView card_title;                                     //消费详情标题
    private TextView tv_row0_0, tv_row0_1;
    private TextView tv_row1_0, tv_row1_1;
    private TextView tv_row2_0, tv_row2_1;
    private TextView tv_row3_0, tv_row3_1;
    private TextView tv_row4_0, tv_row4_2;
    private EditText tv_row4_1;
    private TextView tv_row5_0, tv_row5_1;
    private TextView tv_row6_0, tv_row6_2;
    private EditText tv_row6_1;
    private TextView tv_row7_0, tv_row7_1;

    private TableRow row0, row1, row2, row3, row4, row5, row6, row7;
    private Button ancel_deal;                                      //取消交易
    private Button confirmation;                    //确认交易

    private LoadDataView loadDataView;
    private MessageEntity.MessageConten messageConten;              //
    private String   cardId;                                        //卡ID
    private int    cardType;                                        //卡类型




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumption_details);

        initViews();
        initEvents();
    }

    @Override
    public void initViews() {

        setHeading(true, "消费详情");
        card_title = (TextView) findViewById(R.id.card_title);                  //标题--
        ancel_deal = (Button) findViewById(R.id.ancel_deal);                    //取消交易
        confirmation = (Button) findViewById(R.id.confirmation);                //确认交易
        row0 = (TableRow) findViewById(R.id.row0);
        row1 = (TableRow) findViewById(R.id.row1);
        row2 = (TableRow) findViewById(R.id.row2);
        row3 = (TableRow) findViewById(R.id.row3);
        row4 = (TableRow) findViewById(R.id.row4);
        row5 = (TableRow) findViewById(R.id.row5);
        row6 = (TableRow) findViewById(R.id.row6);
        row7 = (TableRow) findViewById(R.id.row7);
        tv_row0_0 = (TextView) findViewById(R.id.tv_row0_0);
        tv_row0_1 = (TextView) findViewById(R.id.tv_row0_1);

        tv_row1_0 = (TextView) findViewById(R.id.tv_row1_0);
        tv_row1_1 = (TextView) findViewById(R.id.tv_row1_1);

        tv_row2_0 = (TextView) findViewById(R.id.tv_row2_0);
        tv_row2_1 = (TextView) findViewById(R.id.tv_row2_1);

        tv_row3_0 = (TextView) findViewById(R.id.tv_row3_0);
        tv_row3_1 = (TextView) findViewById(R.id.tv_row3_1);

        tv_row4_0 = (TextView) findViewById(R.id.tv_row4_0);
        tv_row4_2 = (TextView) findViewById(R.id.tv_row4_2);

        tv_row5_0 = (TextView) findViewById(R.id.tv_row5_0);
        tv_row5_1 = (TextView) findViewById(R.id.tv_row5_1);

        tv_row6_0 = (TextView) findViewById(R.id.tv_row6_0);
        tv_row6_2 = (TextView) findViewById(R.id.tv_row6_2);

        tv_row7_0 = (TextView) findViewById(R.id.tv_row6_0);
        tv_row7_1 = (TextView) findViewById(R.id.tv_row7_1);

        tv_row4_1 = (EditText) findViewById(R.id.tv_row4_1);
        tv_row6_1 = (EditText) findViewById(R.id.tv_row6_1);
        row0.setVisibility(View.GONE);
        row1.setVisibility(View.GONE);
        row2.setVisibility(View.GONE);
        row3.setVisibility(View.GONE);
        row4.setVisibility(View.GONE);
        row5.setVisibility(View.GONE);
        row6.setVisibility(View.GONE);
        row7.setVisibility(View.GONE);
    }

    @Override
    public void initEvents() {
        View emptyView = findViewById(R.id.rl_layout);
//        ImageView img = (ImageView) emptyView.findViewById(R.id.img_id);
//        TextView ev = (TextView) emptyView.findViewById(R.id.tv_list_view_content);
//        ev.setText("暂无待付款订单");
//        img.setImageResource(R.drawable.icon_pending_payment_waiting_dedault);
        loadDataView = (LoadDataView) emptyView.findViewById(R.id.loadDataView);
        loadDataView.setLoadSucessView(null);
        loadDataView.show();

        messageConten = (MessageEntity.MessageConten)getIntent().getSerializableExtra("MessageConten");
        initViewShow();
    }

    /**
     * 初始化界面
     */
    public void initViewShow() {

//                "title":"消息标题
//                "content":"消息内容
//                "receiveDate":"接收时间
//                "isRead”:”是否已读",
//                "messageType":"消息类型 0系统消息,1消费消息2交易消息",
//                "turnRule":"跳转规则:0(卡券)|卡券类型(1会员卡2优惠券3代金券4储值卡)|卡IID",
//                “initiator”:”消息发起人,如果是为了用户显示手机号码”,

        if(messageConten == null) return;
        try {

            String [] str = messageConten.getTurnRule().split("\\|");
            cardId = str[2];
            cardType = SafetyUitl.tryInt(str[1]);
            switch (SafetyUitl.tryInt(str[1]))
            {
                case 1://会员卡
                    cardId = "27";
                    MessageContentMode.newMessageContentMode().appGetApplyMcardInfo(mContext, cardId, handler, membershipCard);
                    break;
                case 2://优惠券
                    cardId = "36";
                    MessageContentMode.newMessageContentMode().appGetApplyCouponInfo(mContext, cardId, handler, couponsFlag);
                    break;
                case 3://代金券
                    cardId = "10";
                    MessageContentMode.newMessageContentMode().appGetApplyCashcouponInfo(mContext, cardId, handler, vouchersFlag);
                    break;
                case 4://储值卡
                    cardId = "10";
                    MessageContentMode.newMessageContentMode().appGetApplyVcardInfo(mContext, cardId, handler, storageCardFlag);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void initDetailData() {
        switch (cardType)
        {
            case 1:
                card_title.setText("请输入会员本次消费金额");
                row0.setVisibility(View.VISIBLE);
                tv_row0_0.setText("会员昵称");
                tv_row0_1.setText(membershipCardInfoEntity.getMemberNick());

                row1.setVisibility(View.VISIBLE);
                tv_row1_0.setText("会员卡名称");
                tv_row1_1.setText(membershipCardInfoEntity.getMcardName());

                row2.setVisibility(View.VISIBLE);
                tv_row1_0.setText("会员卡编号");
                tv_row1_1.setText(membershipCardInfoEntity.getMcardNo());
                row3.setVisibility(View.INVISIBLE);
                row4.setVisibility(View.VISIBLE);
                tv_row4_0.setText("消费金额");
                tv_row4_2.setText(" 元");

                row6.setVisibility(View.VISIBLE);
                tv_row6_0.setText("赠送积分");

                row7.setVisibility(View.VISIBLE);
                tv_row7_1.setText(membershipCardInfoEntity.getFlowID());

                break;
            case 2:
                card_title.setText("请仔细核对会议及优惠券信息！");
                row0.setVisibility(View.VISIBLE);
                tv_row0_0.setText("会员昵称");
                tv_row0_1.setText(couponsInfoEntity.getMemberNick());

                row1.setVisibility(View.VISIBLE);
                tv_row2_0.setText("优惠券名称");
                tv_row2_1.setText(couponsInfoEntity.getCouponName());

                row2.setVisibility(View.VISIBLE);
                tv_row1_0.setText("优惠券编号");
                tv_row1_1.setText(couponsInfoEntity.getCouponNo());

                row3.setVisibility(View.INVISIBLE);

                row5.setVisibility(View.VISIBLE);
                tv_row5_0.setText("面额");
                tv_row5_1.setText(couponsInfoEntity.getCouponAmount());

                row7.setVisibility(View.VISIBLE);
                tv_row7_1.setText(couponsInfoEntity.getFlowID());

                break;
            case 3:
                card_title.setText("确认前请仔细核对会员的消费信息");
//                voucherUserEntity
                //            "memberNick" : 会员昵称,
//            "cashcouponName":代金券名称,
//            "cashcouponNo":代金券号,
//            "cashcouponId":代金券ID,
//            "cashcouponAmount":代金券面值,
//            "flowID":代金券使用流水号,
//            “approvalState”:”审核状态（2：待审核，3：已审核）(待审核商家可以审核的,已审核则只是查看)”,
//            “approvalResult”:”审核结果（0：不通过，1：通过）通过=交易成功 不通过=交易失败”,
//            “approvalDesc”:” 审核说明（通过备注，不通过原因）”,
                row0.setVisibility(View.VISIBLE);
                tv_row0_0.setText("会员昵称");
                tv_row0_1.setText(voucherUserEntity.getMemberNick());

                row1.setVisibility(View.VISIBLE);
                tv_row2_0.setText("代金券名称");
                tv_row2_1.setText(voucherUserEntity.getCashcouponName());

                row2.setVisibility(View.VISIBLE);
                tv_row1_0.setText("代金券编号");
                tv_row1_1.setText(voucherUserEntity.getCashcouponNo());

                row3.setVisibility(View.INVISIBLE);

                row5.setVisibility(View.VISIBLE);
                tv_row5_0.setText("面额");
                tv_row5_1.setText(voucherUserEntity.getCashcouponAmount());

                row7.setVisibility(View.VISIBLE);
                tv_row7_1.setText(voucherUserEntity.getFlowID());


                break;
            case 4:
                card_title.setText("确认前请仔细核对会员的消费信息");
                row0.setVisibility(View.VISIBLE);
                tv_row0_0.setText("会员昵称");
                tv_row0_1.setText(storageCardInfo.getMemberNick());

                row1.setVisibility(View.VISIBLE);
                tv_row1_0.setText("储值卡名称");
                tv_row1_1.setText(storageCardInfo.getVcardName());

                row2.setVisibility(View.VISIBLE);
                tv_row2_0.setText("储值卡类型");
                tv_row2_1.setText("1".equals(storageCardInfo.getVcardType()) ? "次数卡" : "金额卡");

                row3.setVisibility(View.VISIBLE);
                tv_row3_0.setText("储值卡编号");
                tv_row3_1.setText(storageCardInfo.getVcardNo());

                row4.setVisibility(View.VISIBLE);
                tv_row4_0.setText("消费金额");

                if("2".equals(storageCardInfo.getApprovalState())) {
                    tv_row4_1.setVisibility(View.VISIBLE);
                    tv_row4_2.setText(" 元");
                } else {
                    tv_row4_1.setVisibility(View.GONE);
                    tv_row4_2.setText(storageCardInfo.getConsumerAmount() + "元");
                }



                row5.setVisibility(View.VISIBLE);
                tv_row5_0.setText("卡余额");
                tv_row5_1.setText(storageCardInfo.getResidueVcardAmount());

                row6.setVisibility(View.VISIBLE);

                tv_row6_0.setText("赠送积分");
                tv_row6_1.setVisibility(View.GONE);
                tv_row6_2.setText(storageCardInfo.getIntegralBalance() + " 分");

                row7.setVisibility(View.VISIBLE);
                tv_row7_1.setText(storageCardInfo.getFlowID());

                break;
        }
    }


    @Override
    public void onClick(View v) {
       super.onClick(v);

       switch (v.getId()) {
           case R.id.ancel_deal:     //取消交易
               MessageContentMode.newMessageContentMode().appGetApplyMcardInfo(mContext, cardId, handler, membershipCard);
               break;
           case R.id.confirmation:   //确认交易
               MessageContentMode.newMessageContentMode().appGetApplyMcardInfo(mContext, cardId, handler, membershipCard);
               break;
       }
    }



    //            memberNick : 会员昵称,
//            "mcardName":会员卡名称,
//            "mcardNo":会员卡号,
//            "mcardId":会员卡ID,
//            "integralBalance":赠送积分,
//            "flowID":会员卡使用流水号,
//            “approvalState”:”审核状态（2：待审核，3：已审核）(待审核商家可以审核的,已审核则只是查看)”,
//            “approvalResult”:”审核结果（0：不通过，1：通过）通过=交易成功 不通过=交易失败”,
//            “approvalDesc”:” 审核说明（通过备注，不通过原因）”,


//    "session":"【String】session",
//            "encryptType":”【Int】加密类型 1:AES加密”
//            "idType":1会员卡号,2流水ID,(2流水ID 的使用场景为用户发起的,消息中心进来的)
//            "no":会员卡号,
//            "id":流水ID,
//            "integralBalance":赠送积分
//    "consumeAmount":消费金额
//    "approvalResult":”审核结果（0：不通过，1：通过）”,
//            "approvalDesc":”审核说明”





    private void sendRequest(String flag)
    {
        switch (cardType)
        {
            case 1://会员卡
//                pprovalMcard(idType, no, id, integralBalance, consumeAmount, approvalResult, approvalDesc
                MessageContentMode.newMessageContentMode().appApprovalMcard(mContext,
                        "2"
                        ,membershipCardInfoEntity.getMcardNo()
                        ,membershipCardInfoEntity.getFlowID()
                        ,membershipCardInfoEntity.getIntegralBalance()
                        ,""
                        ,flag
                        ,membershipCardInfoEntity.getApprovalDesc(), handlerRequest);
                break;
            case 2://优惠券
                //    memberNick : 会员昵称,
//            "couponName":优惠券名称,
//            "couponNo":优惠券号,
//            "couponId":优惠券ID,
//            "couponAmount":优惠券面值,
//            "flowID":优惠券使用流水号
//    “approvalState”:”审核状态（2：待审核，3：已审核）(待审核商家可以审核的,已审核则只是查看)”,
//            “approvalResult”:”审核结果（0：不通过，1：通过）通过=交易成功 不通过=交易失败”,
//            “approvalDesc”:” 审核说明（通过备注，不通过原因）”,
//                final String idType, final String no, final String id, final String approvalResult,final String approvalDesc,
                MessageContentMode.newMessageContentMode().appApprovalCoupon(mContext,
                        "2"
                        ,couponsInfoEntity.getCouponNo()
                        ,couponsInfoEntity.getFlowID()
                        ,flag
                        ,couponsInfoEntity.getApprovalDesc(), handlerRequest);
                break;
            case 3://代金券
                //            "memberNick" : 会员昵称,
//            "cashcouponName":代金券名称,
//            "cashcouponNo":代金券号,
//            "cashcouponId":代金券ID,
//            "cashcouponAmount":代金券面值,
//            "flowID":代金券使用流水号,
//            “approvalState”:”审核状态（2：待审核，3：已审核）(待审核商家可以审核的,已审核则只是查看)”,
//            “approvalResult”:”审核结果（0：不通过，1：通过）通过=交易成功 不通过=交易失败”,
//            “approvalDesc”:” 审核说明（通过备注，不通过原因）”,
                MessageContentMode.newMessageContentMode().appApprovalCashcoupon(mContext,
                        "2"
                        ,voucherUserEntity.getCashcouponNo()
                        ,voucherUserEntity.getFlowID()
                        ,flag
                        ,voucherUserEntity.getApprovalDesc(), handlerRequest);


                break;
            case 4://储值卡

                //    "memberNick":会员昵称,
//            "vcardName":储值卡名称,
//            "vcardNo":储值卡号,
//            "vcardId":储值卡ID,
//            "vcardAmount":储值卡面值,
//            "consumerAmount":储值卡消费金额,
//            "vcardType":1：次数卡，2：金额卡,
//            "residueVcardAmount":剩余储值卡面值,
//            "integralBalance":赠送积分,
//            "flowID":会员卡使用流水号,
//            “approvalState”:”审核状态（2：待审核，3：已审核）(待审核商家可以审核的,已审核则只是查看)”,
//            “approvalResult”:”审核结果（0：不通过，1：通过）通过=交易成功 不通过=交易失败”,
//            “approvalDesc”:” 审核说明（通过备注，不通过原因）”,
                MessageContentMode.newMessageContentMode().appApprovalVcard(mContext,
                        "2"
                        ,storageCardInfo.getVcardNo()
                        ,storageCardInfo.getFlowID()
                        ,storageCardInfo.getIntegralBalance()
                        ,""
                        ,flag
                        ,storageCardInfo.getApprovalDesc(), handlerRequest);
                break;
        }

    }


    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case NetCode.RequestSuccess:
                    loadDataView.closed(LoadDataView.LoadResponse.Sucess);
                    switch (msg.arg2)
                    {
                        case membershipCard:   //会员卡
                            membershipCardInfoEntity = (MembershipCardInfoEntity) msg.obj;
                            break;
                        case couponsFlag:      //优惠券
                            couponsInfoEntity = (CouponsInfoEntity) msg.obj;
                            break;
                        case vouchersFlag:     //代金券
                            voucherUserEntity = (VoucherUserEntity) msg.obj;
                            break;
                        case storageCardFlag:  //储值卡
                            storageCardInfo = (StorageCardInfo) msg.obj;
                            break;

                        default:break;
                    }
                    initDetailData();
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

    Handler handlerRequest = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case NetCode.RequestSuccess:



                    break;
                case NetCode.RequestFailed:

                    break;
                case NetCode.System_Error:

                    break;
                default:
                    break;
            }
        }
    };

}
