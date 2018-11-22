package com.umeng.shopcar_oneone_twotwo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by 择木 on 2018/11/22.
 */

public class MyAddJian  extends LinearLayout implements View.OnClickListener {

    TextView btnAdd;
    TextView tvNum;
    TextView btnJian;
    private int number = 1;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        tvNum.setText(number+"");
    }

    public MyAddJian(Context context) {
        this(context,null);
    }

    public MyAddJian(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View inflate = View.inflate(context, R.layout.item_addorjian, this);
        btnAdd = inflate.findViewById(R.id.btn_add);
        tvNum = inflate.findViewById(R.id.tv_num);
        btnJian = inflate.findViewById(R.id.btn_jian);
        btnAdd.setOnClickListener(this);
        btnJian.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                if (number>1){
                    --number;
                    tvNum.setText(number+"");
                    if (onNumberChangeListener != null){
                        onNumberChangeListener.OnNumberChange(number);
                    }
                }else {
                    Toast.makeText(getContext(), "不买滚犊子!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_jian:
                ++number;
                tvNum.setText(number+"");
                if (onNumberChangeListener!=null){
                    onNumberChangeListener.OnNumberChange(number);
                }
                break;
        }

    }
    public interface OnNumberChangeListener {

        void OnNumberChange(int number);


    }
    OnNumberChangeListener onNumberChangeListener;
    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }
}
