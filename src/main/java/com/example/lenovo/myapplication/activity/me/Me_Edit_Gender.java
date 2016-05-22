package com.example.lenovo.myapplication.activity.me;

import android.app.Activity;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.lenovo.myapplication.R;

public class Me_Edit_Gender extends Activity {
    RadioButton male,female;
    Button submit,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me__edit__gender);
        inits();
        setListeners();
    }

    private void setListeners() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message=Message.obtain();
                message.what=Me_Edit.GENDER;
                if(male.isChecked()){

                    message.obj="男";
                    Me_Edit.MEHandler.sendMessage(message);
                    finish();
                }
                if(female.isChecked()){
                    message.obj="女";
                    Me_Edit.MEHandler.sendMessage(message);
                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inits() {
        male=(RadioButton)findViewById(R.id.me_gender_male);
        female=(RadioButton)findViewById(R.id.me_gender_female);
        submit=(Button)findViewById(R.id.me_gender_submit);
        cancel=(Button)findViewById(R.id.me_gender_cancel);
    }

}
