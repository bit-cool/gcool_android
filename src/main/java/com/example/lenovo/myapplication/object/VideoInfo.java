package com.example.lenovo.myapplication.object;

/**
 * Created by lenovo on 2016/4/21.
 */
public class VideoInfo {
    String imgUrl;
    String userName;
    String userIcon;
    int imgHeight;
    int imag; //测试图片
    public int getImag() {
        return imag;
    }

    public void setImag(int imag) {
        this.imag = imag;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
