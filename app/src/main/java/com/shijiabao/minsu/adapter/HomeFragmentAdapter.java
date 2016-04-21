package com.shijiabao.minsu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shijiabao.minsu.R;

/**
 * Created by prx on 2016/4/15 01:17.
 * Email:pangrongxian@gmail.com
 */
public class HomeFragmentAdapter extends BaseAdapter{

    private Context context;
    private String[] imageList;
    private LayoutInflater inflater;

    public HomeFragmentAdapter(Context context, String[] imageList) {
        this.context = context;
        this.imageList = imageList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imageList.length;
    }

    @Override
    public Object getItem(int position) {
        return imageList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View cv, ViewGroup parent) {
        ViewHolder holder = null;
        if (cv == null){
            holder = new ViewHolder();
            cv = inflater.inflate(R.layout.item_listview_home,null);
            holder.home_item_lv_image = (ImageView) cv.findViewById(R.id.home_item_image);

            holder.home_item_lv_money = (TextView) cv.findViewById(R.id.home_item_lv_money);
            holder.home_item_lv_title = (TextView) cv.findViewById(R.id.home_item_lv_titleText);
            holder.home_item_lv_text = (TextView) cv.findViewById(R.id.home_item_lv_text);
            holder.home_item_lv_city = (TextView) cv.findViewById(R.id.home_item_lv_city);
            holder.home_item_lv_integral = (TextView) cv.findViewById(R.id.home_item_lv_integral);
            holder.home_item_lv_common = (TextView) cv.findViewById(R.id.home_item_lv_common);
            cv.setTag(holder);
        }else {
            holder = (ViewHolder) cv.getTag();
        }

        holder.home_item_lv_money.setText("700"+position);
        holder.home_item_lv_title.setText("花盛美地度假屋"+position);
        holder.home_item_lv_text.setText("—唯美度假城堡，在烂漫的树上泡温泉"+position);
        holder.home_item_lv_city.setText("广州"+position);
        holder.home_item_lv_integral.setText("9.0分"+position);
        holder.home_item_lv_common.setText("23评论"+position);

        Glide.with(context)
                .load(imageList[position])//url
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(holder.home_item_lv_image);

        return cv;
    }


   public class  ViewHolder{
       public ImageView home_item_lv_image,home_item_lv_hard;
       public TextView
               home_item_lv_money,
               home_item_lv_title,
               home_item_lv_text,
               home_item_lv_city,
               home_item_lv_integral,
               home_item_lv_common;
    }

}
