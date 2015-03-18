package com.example.calendardemo.fragment;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calendardemo.CalendarActivity;
import com.example.calendardemo.R;
import com.example.calendardemo.util.CalendarUtils;

public class CalendarMonthFragment extends Fragment {
	private ViewPager mMonthsViewPager;

	public static CalendarMonthFragment newInstance() {
		CalendarMonthFragment details = new CalendarMonthFragment();
		Bundle args = new Bundle();
		// args.putSerializable("bean", mBean);
		details.setArguments(args);
		return details;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_calendar_month, null);
		initValues();
		initViews(view);
		initData();
		return view;
	}

	private void initData() {
		// TODO Auto-generated method stub

	}

	private void initViews(View view) {
		// TODO Auto-generated method stub
		mMonthsViewPager = (ViewPager) view
				.findViewById(R.id.calendar_months_vp);
		final ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(
				getActivity().getSupportFragmentManager());
		mMonthsViewPager.setAdapter(screenSlidePagerAdapter);
		// mMonthsViewPager.setOffscreenPageLimit(3);//“3”代表的是：加载的页数

		mMonthsViewPager.setCurrentItem(CalendarUtils.CURRENT_PAGENO);

		mMonthsViewPager
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

					@Override
					public void onPageSelected(int position) {
						Calendar cl = CalendarUtils
								.getInstance()
								.getSelectedMonth(
										position,
										(Calendar) ((CalendarActivity) getActivity())
												.getCurrentMonthCalendar()
												.clone());

						((CalendarActivity) getActivity()).getDateTv().setText(
								cl.get(Calendar.YEAR) + "年"
										+ (cl.get(Calendar.MONTH) + 1) + "月");

						/**同步周fragment和日fragment**/
						((CalendarActivity) getActivity())
								.setCurrentWeekCalendar((Calendar) cl.clone());
						((CalendarActivity) getActivity())
								.setCurrentDayCalendar((Calendar) cl.clone());
					}

					@Override
					public void onPageScrolled(int position,
							float positionOffset, int positionOffsetPixels) {

					}

					@Override
					public void onPageScrollStateChanged(int state) {

					}
				});
	}

	private void initValues() {
		// TODO Auto-generated method stub
		/**同步周fragment和日fragment**/
		((CalendarActivity) getActivity())
				.setCurrentWeekCalendar((Calendar) ((CalendarActivity) getActivity())
						.getCurrentMonthCalendar().clone());
		((CalendarActivity) getActivity())
				.setCurrentDayCalendar((Calendar) ((CalendarActivity) getActivity())
						.getCurrentMonthCalendar().clone());
	}

	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return MonthFragment.create(position);
		}

		@Override
		public int getCount() {
			return 1000;
		}
	}
}
