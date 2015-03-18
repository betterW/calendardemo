package com.example.calendardemo.base;

import java.lang.reflect.Field;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

/**
 * FragemntActivity基类
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements
		SurfaceStandard {

	public abstract int getLayoutResID();
	public abstract void intView();

	public Context context;

	@Override
	protected final void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(getLayoutResID());
		this.context = this;
		if (this instanceof SurfaceStandard) {
			((SurfaceStandard) this).initValues();
			((SurfaceStandard) this).initViews();
			((SurfaceStandard) this).initControl();
			((SurfaceStandard) this).initData();
			((SurfaceStandard) this).updateData();
			((SurfaceStandard) this).recovery();
		}
	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		autoInjectAllField();
		intView();
	}

	/**
	 * 解析注解
	 */
	public void autoInjectAllField() {
		try {
			Class<?> clazz = this.getClass();
			Field[] fields = clazz.getDeclaredFields();// 获得Activity中声明的字段
			for (Field field : fields) {
				// 查看这个字段是否有我们自定义的注解类标志的
				if (field.isAnnotationPresent(ViewInject.class)) {
					ViewInject inject = field.getAnnotation(ViewInject.class);
					int id = inject.value();
					if (id > 0) {
						field.setAccessible(true);
						field.set(this, this.findViewById(id));// 给我们要找的字段设置值
					}
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}
