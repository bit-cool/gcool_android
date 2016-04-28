package com.example.lenovo.myapplication.object;

/**
 * Created by lenovo on 2016/4/23.
 */
public class AmdInfo {
    /*imgUrl:图片url
      tag: amd活动标签类型
      author:amd活动发起人
      agreeNum:点赞数
    * */
    String imgUrl;
    String tag;
    String author;
    String agreeNum;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(String agreeNum) {
        this.agreeNum = agreeNum;
    }
}
