package com.dianna.spreadsheetview;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GridviewAdapter extends BaseAdapter {
    private List<Model> mDatas;
    private LayoutInflater inflater;
    private int curIndex;//页数下标,从0开始
    private int pageSize;//每一页显示的个数
    private int textSize;
    private int textColor;

    public GridviewAdapter(Context context,List<Model> mDatas,int curIndex, int pageSize,int textSize,int textColor) {
        this.mDatas = mDatas;
        this.curIndex = curIndex;
        this.pageSize = pageSize;
        this.textSize=textSize;
        this.textColor=textColor;
        inflater=LayoutInflater.from(context);
    }

    //先判断数据集的大小是否足够显示满本页，如果够，则直接返回每一页显示的最大条目个数pagesize
    @Override
    public int getCount() {
        return mDatas.size()>(curIndex+1)*pageSize?pageSize:(mDatas.size()- curIndex * pageSize);
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position+curIndex*pageSize);
    }

    @Override
    public long getItemId(int position) {
        return position+curIndex*pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_gridview,null);
            viewHolder=new ViewHolder();
            viewHolder.tv=convertView.findViewById(R.id.textview);
            viewHolder.iv=convertView.findViewById(R.id.imageview);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        int pos=position+curIndex*pageSize;
        viewHolder.tv.setTextSize(textSize);
        viewHolder.tv.setTextColor(textColor);
        viewHolder.tv.setText(mDatas.get(pos).getName());
        viewHolder.iv.setImageResource(mDatas.get(pos).getiCon());
        return convertView;
    }

    class ViewHolder{
        public TextView tv;
        public ImageView iv;
    }
}
