package com.example.calendardemo.base;

/*
 * 这个接口只是希望界面都是 以这种方式来写
 */
public interface SurfaceStandard {
	// 这个方法里面设置初始值
	public void initValues();

	// 这个方法里面初始化数据
	public void initData();

	// 这个界面设置可视化控件
	public void initViews();

	// 这个方法里面写控件的控制
	public void initControl();

	// 更新数据
	public void updateData();

	// 如果有必要，这个方法做内存回收
	public void recovery();

}
