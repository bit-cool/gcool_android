package com.example.lenovo.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.object.VideoInfo;
import com.example.lenovo.myapplication.utils.UIUtils;

import java.util.List;

/**
 * Created by lenovo on 2016/4/21.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    private Context context;
    private List<VideoInfo> data;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerAdapter(Context context, List<VideoInfo> data) {
        this.context = context;
        this.data = data;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int postion);

        void onItemLongClick(View view, int postion);
    }

    public void setOnClickListener(OnItemClickListener listener) {//对外提供的一个监听方法
        this.mOnItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_source_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( final MyViewHolder holder, int position) {
        ViewGroup.LayoutParams params=holder.videoImg.getLayoutParams();    //该item的布局参数
        int screenWidth = UIUtils.getScreenWidth();                         //一列的宽度 = 屏幕宽度 - 图片之间的间隙 / 2 (两列)
        int myWidth = (screenWidth - UIUtils.dip2px(16)) / 2;
        params.height=data.get(position).getImgHeight();                    //设置item高度为服务器传来的图片高度
        params.width=myWidth;
        holder.itemView.setLayoutParams(new FrameLayout.LayoutParams(myWidth,data.get(position).getImgHeight()));                            //将布局设置给holder
        holder.userName.setText(data.get(position).getUserName());
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,pos);

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView,pos);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        ImageView videoImg;
        ImageView userIcon;
        public MyViewHolder(View itemView) {
            super(itemView);
            userName=(TextView)itemView.findViewById(R.id.video_source_username);
            videoImg=(ImageView)itemView.findViewById(R.id.video_source_img);
            userIcon=(ImageView)itemView.findViewById(R.id.video_source_usericon);
        }
    }
}


