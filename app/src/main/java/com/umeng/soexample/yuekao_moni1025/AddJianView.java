package com.umeng.soexample.yuekao_moni1025;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 择木 on 2018/10/25.
 */

public class AddJianView  extends RelativeLayout implements View.OnClickListener {
    private TextView tvAdd;
    private TextView tvJian;
    private TextView tvNum;

    private int num;

    public interface OnAddJianClickListener {
        void add(int num);

        void jian(int num);
    }


    private OnAddJianClickListener listener;

    public void setOnAddJianClickListener(OnAddJianClickListener listener) {
        this.listener = listener;
    }

    public AddJianView(Context context) {
        this(context, null);
    }

    public AddJianView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddJianView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.item_addjian, this);
        tvAdd = findViewById(R.id.tv_add);
        tvJian = findViewById(R.id.tv_add);
        tvNum = findViewById(R.id.tv_num);

        tvNum.setText("1");

        tvAdd.setOnClickListener(this);
        tvJian.setOnClickListener(this);

    }

    public void setNum(int num) {
        this.num = num;
        tvNum.setText(num + "");
    }

    public int getNum() {
        return num;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add:
                num++;
                tvNum.setText(num + "");
                if (listener != null) {
                    listener.add(num);
                }
                break;
            case R.id.tv_jian:
                if (num > 1) {
                    num--;
                }
                tvNum.setText(num + "");
                if (listener != null) {
                    listener.jian(num);
                }
                break;
        }
    }
}
