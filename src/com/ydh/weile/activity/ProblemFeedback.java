package com.ydh.weile.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ydh.weile.android.BaseActivity;
import com.ydh.weile.merchant.R;
import com.ydh.weile.uitl.ToastUitl;

/**
 * Created by liujianying on 14-10-10.
 * @问题反馈
 */
public class ProblemFeedback extends BaseActivity implements View.OnTouchListener {

    private EditText feedback_content;
    private Button feedback_submit;
    private RelativeLayout rl_problem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_feedback);
        initViews();
        initEvents();
    }

    @Override
    public void initViews() {
        setHeading(true, "问题反馈");

        feedback_content = (EditText) findViewById(R.id.feedback_content);
        feedback_submit = (Button) findViewById(R.id.feedback_submit);
        rl_problem = (RelativeLayout) findViewById(R.id.rl_problem);
    }

    @Override
    public void initEvents() {

        rl_problem.setOnTouchListener(this);
        feedback_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUitl.showToast(mContext, "问题已经提交，感谢您的反馈");
            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        HidKeyBoard(motionEvent);
        return false;
    }
}
