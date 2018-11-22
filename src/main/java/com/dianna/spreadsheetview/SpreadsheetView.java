package com.dianna.spreadsheetview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import static android.view.Gravity.CENTER;
import static android.widget.LinearLayout.HORIZONTAL;

public class SpreadsheetView extends RelativeLayout {
    private int pageSize = 5;//每一页显示的最大个数
    private int pageCount;//总页数
    private int curIndex = 0;//当前显示的是第几页
    private List<Model> modelList;//数据
    private ViewPager viewPager;
    private Context context;
    private int num = 6;//几列
    private List<View> gridViewList;
    private ViewPagerAdapter viewPagerAdapter;
    private LinearLayout linearLayout;
    private LayoutInflater layoutInflater;
    private int backColor=Color.WHITE;
    private int linearColor=Color.WHITE;
    private int pointWidth=30;
    private int pointHeight=30;
    private int pointMarginLeft=5;
    private int pointMarginRight=5;
    private int pointMarginTop=0;
    private int pointMarginBottom=0;
    private int pointImage=R.drawable.tuitional_carousel_btn;
    private int pointCheckImage=R.drawable.tuitional_carousel_active_btn;
    private int textSize=13;
    private int textColor=Color.BLACK;
    private int imageMarginTop=20;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SpreadsheetView(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public SpreadsheetView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SpreadsheetView setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public SpreadsheetView setNum(int num) {
        this.num = num;
        return this;
    }

    public SpreadsheetView setModelList(List<Model> modelList){
        this.modelList=modelList;
        return this;
    }

    public SpreadsheetView setBackColor(int color){
        this.backColor=color;
        return this;
    }

    public SpreadsheetView setLinearColor(int color){
        this.linearColor=color;
        return this;
    }

    public SpreadsheetView setPointWidth(int width){
        this.pointWidth=width;
        return this;
    }

    public SpreadsheetView setPointHeight(int height){
        this.pointHeight=height;
        return this;
    }

    public SpreadsheetView setPointMarginLeft(int left){
        this.pointMarginLeft=left;
        return this;
    }

    public SpreadsheetView setPointMarginRight(int right){
        this.pointMarginRight=right;
        return this;
    }

    public SpreadsheetView setPointMarginTop(int top){
        this.pointMarginTop=top;
        return this;
    }

    public SpreadsheetView setPointMarginBottom(int bottom){
        this.pointMarginBottom=bottom;
        return this;
    }

    public SpreadsheetView setPointImage(int image){
        this.pointImage=image;
        return this;
    }

    public SpreadsheetView setPointCheckImage(int image){
        this.pointCheckImage=image;
        return this;
    }

    public SpreadsheetView setTextSize(int size){
        this.textSize=size;
        return this;
    }

    public SpreadsheetView setTextColor(int color){
        this.textColor=color;
        return this;
    }

    public SpreadsheetView setImageMarginTop(int marginTop){
        this.imageMarginTop=marginTop;
        return this;
    }

    public SpreadsheetView init() {
        //总的页数=总数/每页数量,并取整
        pageCount = (int) Math.ceil(modelList.size() * 1.0 / pageSize);
        gridViewList = new ArrayList<>();
        layoutInflater=LayoutInflater.from(context);
        LinearLayout.LayoutParams layoutParamsss=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < pageCount; i++) {
            GridView gridView = (GridView) layoutInflater.inflate(R.layout.gridview, null);
            gridView.setNumColumns(num);
            //gridView.setTop(imageMarginTop);
            gridView.setVerticalSpacing(imageMarginTop);
            gridView.setAdapter(new GridviewAdapter(context, modelList, i, pageSize,textSize,textColor));
            gridViewList.add(gridView);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    onItemClickListener.onItemClick(curIndex,position);
                }
            });
        }
        viewPager = new ViewPager(context);
        viewPagerAdapter = new ViewPagerAdapter(gridViewList);
        viewPager.setAdapter(viewPagerAdapter);

        linearLayout = new LinearLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);//这个就是添加其他属性的，这个是在父元素的底部。
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setGravity(CENTER);
        linearLayout.setBackgroundColor(linearColor);
        linearLayout.setLayoutParams(layoutParams);


        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(pointWidth, pointHeight);
        layoutParams1.leftMargin = pointMarginLeft;
        layoutParams1.rightMargin = pointMarginRight;
        layoutParams1.topMargin = pointMarginTop;
        layoutParams1.bottomMargin = pointMarginBottom;
        for (int i = 0; i < pageCount; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(layoutParams1);
            imageView.setBackgroundResource(pointImage);
            linearLayout.addView(imageView);
        }

        linearLayout.getChildAt(0).setBackgroundResource(pointCheckImage);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //取消圆点
                linearLayout.getChildAt(curIndex).setBackgroundResource(pointImage);
                linearLayout.getChildAt(position).setBackgroundResource(pointCheckImage);
                curIndex = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        this.addView(viewPager);
        this.addView(linearLayout);
        this.setBackgroundColor(backColor);

        return this;
    }

    public interface OnItemClickListener{
        void onItemClick(int curIndex,int postion);
    }
}
