package com.example.lenovo.myapplication.activity.me;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class Me_Edit extends AppCompatActivity {

    static ImageView touXiang;
    static TextView qianMing;
    static TextView gender;
    static TextView birthday;
    static TextView niCheng;
    ImageView back;
    TextView save;

    public static final int QIANMING=1;
    public static final int TOUXIANG=2;
    public static final int GENDER=3;
    public static final int BIRTHDAY=4;
    public static final int NICHENG=5;

    public static Handler MEHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int num=msg.what;
            if(num==QIANMING){
                qianMing.setText(msg.obj.toString());
            }
            if(num==NICHENG){
                niCheng.setText(msg.obj.toString());
            }
            if(num==BIRTHDAY){
                birthday.setText(msg.obj.toString());
            }
            if(num==GENDER){
                gender.setText(msg.obj.toString());
            }
            if(num==TOUXIANG){

            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me__edit);
        initViews();
        setListeners();
        setContents();
    }
    //从服务器读取个人信息
    private void setContents() {

    }

    private void initViews() {
        touXiang=(ImageView)findViewById(R.id.me_edit_touxiang);
        qianMing=(TextView)findViewById(R.id.me_edit_qianming);
        gender=(TextView)findViewById(R.id.me_edit_gender);
        birthday=(TextView)findViewById(R.id.me_edit_birthday);
        niCheng=(TextView)findViewById(R.id.me_edit_nicheng);

        back=(ImageView)findViewById(R.id.me_edit_back);
        save=(TextView)findViewById(R.id.me_edit_save);


    }

    private void setListeners(){
        touXiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,1);
            }
        });

        qianMing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Me_Edit_Qianming.class);
                startActivity(intent);
            }
        });
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Me_Edit_Gender.class);
                startActivity(intent);
            }
        });

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        niCheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Me_Edit_Nicheng.class);
                startActivity(intent);
            }
        });

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
                finish();
            }
        });
    }
    //上传到服务器
    private void sendToServer() {

    }

    public void showDatePickerDialog(){
        DatePickerFragment datePicker = new DatePickerFragment();
        datePicker.show(getFragmentManager(), "datePicker");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            Uri uri = data.getData();
            Log.e("uri",String.valueOf(uri));
            switch (requestCode){
                case 1:
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(uri, "image/*");
                    // 设置裁剪
                    intent.putExtra("crop", "true");
                    // aspectX aspectY 是宽高的比例
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    // outputX outputY 是裁剪图片宽高
                    intent.putExtra("outputX", 300);
                    intent.putExtra("outputY", 300);
                    intent.putExtra("outputFormat", "PNG");
                    intent.putExtra("noFaceDetection", true);
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, 2);
                    break;
                case 2:
                    Bitmap photo = null;
                    Uri photoUri = data.getData();
                    if (photoUri != null) {
                        photo = BitmapFactory.decodeFile(photoUri.getPath());
                    }
                    if (photo == null) {
                        Bundle extra = data.getExtras();
                        if (extra != null) {
                            photo = (Bitmap)extra.get("data");
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        }
                    }
                    touXiang.setImageBitmap(photo);
                    break;
            }

        }

    }
}
