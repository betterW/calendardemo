package com.example.calendardemo;

import java.util.Calendar;
import java.util.Locale;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.calendardemo.base.BaseFragmentActivity;
import com.example.calendardemo.base.ViewInject;
import com.example.calendardemo.fragment.CalendarDayFragment;
import com.example.calendardemo.fragment.CalendarMonthFragment;
import com.example.calendardemo.fragment.CalendarWeekFragment;
import com.example.calendardemo.util.CalendarUtils;

public class CalendarActivity extends BaseFragmentActivity implements OnClickListener{
	@ViewInject(R.id.calendar_switch_month)
	private TextView mSwitchBtn_month;
	@ViewInject(R.id.calendar_switch_week)
	private TextView mSwitchBtn_week;
	@ViewInject(R.id.calendar_switch_day)
	private TextView mSwitchBtn_day;
	@ViewInject(R.id.calendar_current_date)
	private TextView mDateTv;

	private Calendar mCurrentMonthCalendar;
	private Calendar mCurrentWeekCalendar;
	private Calendar mCurrentDayCalendar;

	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		Calendar cl = Calendar.getInstance(Locale.CHINA);
		setCurrentMonthCalendar((Calendar) cl.clone());
		setCurrentWeekCalendar((Calendar) cl.clone());
		setCurrentDayCalendar((Calendar) cl.clone());
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initControl() {
		// TODO Auto-generated method stub
		mSwitchBtn_month.setOnClickListener(this);
		mSwitchBtn_week.setOnClickListener(this);
		mSwitchBtn_day.setOnClickListener(this);
	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void recovery() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutResID() {
		// TODO Auto-generated method stub
		return R.layout.calendar_main;
	}

	@Override
	public void intView() {
		// TODO Auto-generated method stub
//		mSwitchBtn_month.setSelected(true);
		setSelectTab(0);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.calendar_switch_month:
			setSelectTab(0);
			break;
		case R.id.calendar_switch_week:
			setSelectTab(1);
			break;
		case R.id.calendar_switch_day:
			setSelectTab(2);
			break;
		}
	}
	public void setSelectTab(int index) {
		switch (index) {
		case 0:
			mSwitchBtn_month.setSelected(true);
			mSwitchBtn_week.setSelected(false);
			mSwitchBtn_day.setSelected(false);
			break;
		case 1:
			mSwitchBtn_month.setSelected(false);
			mSwitchBtn_week.setSelected(true);
			mSwitchBtn_day.setSelected(false);
			break;
		case 2:
			mSwitchBtn_month.setSelected(false);
			mSwitchBtn_week.setSelected(false);
			mSwitchBtn_day.setSelected(true);
			break;
		}
		setDate(index);
		loadFragments(index);
	}
	private void setDate(int index) {
		switch (index) {
		case 0:
			Calendar cl = getCurrentMonthCalendar();

			// CalendarUtils.getInstance().getSelectedMonth(
			// CalendarUtils.CURRENT_PAGENO);

			mDateTv.setText(cl.get(Calendar.YEAR) + "年"
					+ (cl.get(Calendar.MONTH) + 1) + "月");
			break;
		case 1:
			cl = getCurrentWeekCalendar();

			// CalendarUtils.getInstance().getSelectedWeekOfYear(
			// CalendarUtils.CURRENT_PAGENO);
			mDateTv.setText(cl.get(Calendar.YEAR) + "年 " + "第("
					+ cl.get(Calendar.WEEK_OF_YEAR) + ")周");
			break;
		case 2:
			cl = getCurrentDayCalendar();

			// CalendarUtils.getInstance().getSelectedDayOfMonth(
			// CalendarUtils.CURRENT_PAGENO);
			mDateTv.setText(cl.get(Calendar.YEAR)
					+ "年"
					+ (cl.get(Calendar.MONTH) + 1)
					+ "月"
					+ cl.get(Calendar.DAY_OF_MONTH)
					+ "日 "
					+ CalendarUtils.getInstance().getWeekday(
							cl.get(Calendar.DAY_OF_WEEK)));
			break;
		}
	}

	/**
	 * 加载fragment
	 * */
	private void loadFragments(int index) {
		FragmentTransaction mFgtTransaction = getSupportFragmentManager()
				.beginTransaction();
		hideFragments(mFgtTransaction);
		switch (index) {
		case 0:
			mFgtTransaction.replace(R.id.calendar_fragment_container,
					CalendarMonthFragment.newInstance());
			break;
		case 1:
			mFgtTransaction.replace(R.id.calendar_fragment_container,
					CalendarWeekFragment.newInstance());
			break;
		case 2:
			mFgtTransaction.replace(R.id.calendar_fragment_container,
					CalendarDayFragment.newInstance());
			break;
		}
		mFgtTransaction.commitAllowingStateLoss();
	}

	private void hideFragments(FragmentTransaction mFgtTransaction) {
		// TODO Auto-generated method stub
	}

	public TextView getDateTv() {
		return mDateTv;
	}
	public Calendar getCurrentMonthCalendar() {
		return mCurrentMonthCalendar;
	}

	public void setCurrentMonthCalendar(Calendar mCurrentMonthCalendar) {
		this.mCurrentMonthCalendar = mCurrentMonthCalendar;
	}

	public Calendar getCurrentWeekCalendar() {
		return mCurrentWeekCalendar;
	}

	public void setCurrentWeekCalendar(Calendar mCurrentWeekCalendar) {
		this.mCurrentWeekCalendar = mCurrentWeekCalendar;
	}

	public Calendar getCurrentDayCalendar() {
		return mCurrentDayCalendar;
	}

	public void setCurrentDayCalendar(Calendar mCurrentDayCalendar) {
		this.mCurrentDayCalendar = mCurrentDayCalendar;
	}
}
