package com.example.lenovo.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.object.AmdInfo;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/23.
 */
public class AMDListAdapter extends BaseAdapter {
    ArrayList<AmdInfo> list;
    LayoutInflater inflater;
    public AMDListAdapter(Context context,ArrayList<AmdInfo> list) {
        this.list=list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.amd_doing_item,null);
            holder.img=(ImageView)view.findViewById(R.id.amd_doing_item_pic);
            holder.topic=(TextView)view.findViewById(R.id.amd_doing_item_topic);
            holder.author=(TextView)view.findViewById(R.id.amd_doing_item_author);
            holder.agreeNum=(TextView)view.findViewById(R.id.amd_doing_item_agree);
            holder.tag=(ImageView)view.findViewById(R.id.amd_doing_item_tag);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }
        holder.img.setImageResource(R.drawable.test);
        holder.author.setText(list.get(position).getAuthor());
        holder.topic.setText(list.get(position).getName());
        holder.agreeNum.setText(list.get(position).getAgreeNum());
        //测试，实际应该依照传来的标签类型设置
        holder.tag.setImageResource(R.drawable.ic_launcher);
        return view;
    }

    class ViewHolder{
        ImageView img;
        TextView topic;
        TextView author;
        TextView agreeNum;
        ImageView tag;

    }
}
