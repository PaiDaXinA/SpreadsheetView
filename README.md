# SpreadsheetView
    Android滚动表格视图
	 spreadsheetView.setModelList(modelList)//填充数据
                .setNum(4)//设置列数
                .setPageSize(8)//设置一页多少个item
                .setBackColor(Color.WHITE)//设置列表背景
                .setLinearColor(Color.WHITE)//设置圆点背景
                .setPointWidth(20)//设置圆点宽度
                .setPointHeight(20)//设置圆点高度
                .setPointMarginLeft(5)//设置圆点左边距
                .setPointMarginRight(5)//设置圆点右边距
                .setPointMarginBottom(5)//设置圆点下边距
                .setPointMarginTop(5)//设置圆点上边距
                .setTextSize(13)//设置item标题的大小
                .setTextColor(Color.BLACK)//设置item标题的颜色
                .setPointImage(R.drawable.tuitional_carousel_btn)//设置圆点的图片
                .setPointCheckImage(R.drawable.tuitional_carousel_active_btn)//设置圆点选中的图片
                .setImageMarginTop(20)//设置gridview的行宽
                .init();//初始化
                
	spreadsheetView.setOnItemClickListener(new SpreadsheetView.OnItemClickListener() {
            @Override
            public void onItemClick(int curIndex, int postion) {
                //item当前position+当前页数下标*一页item数
                int pos = postion + curIndex * pageSize;
                Toast.makeText(MainActivity.this, modelList.get(pos).getName(), Toast.LENGTH_SHORT).show();
            }
        });
