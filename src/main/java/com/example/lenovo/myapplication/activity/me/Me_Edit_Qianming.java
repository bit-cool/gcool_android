package com.example.lenovo.myapplication.activity.me;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;

public class Me_Edit_Qianming extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me__edit__qianming);
        ImageView back=(ImageView)findViewById(R.id.me_qianming_back);
        TextView save=(TextView)findViewById(R.id.me_qianming_save);
        final EditText qianming=(EditText)findViewById(R.id.me_qianming_text);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToServer();
                Message message=Message.obtain();
                message.what=Me_Edit.QIANMING;
                message.obj=qianming.getText();
                Me_Edit.MEHandler.sendMessage(message);
                finish();
            }
        });
    }
    //将修改提交到服务器
    private void sendToServer() {

    }



}
