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

public class CalendarWeekFragment extends Fragment {
	private ViewPager mWeeksViewPager;

	public static CalendarWeekFragment newInstance() {
		CalendarWeekFragment details = new CalendarWeekFragment();
		Bundle args = new Bundle();
		// args.putSerializable("bean", mBean);
		details.setArguments(args);
		return details;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_calendar_week, null);
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
		mWeeksViewPager = (ViewPager) view.findViewById(R.id.calendar_weeks_vp);
		final ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(
				getActivity().getSupportFragmentManager());
		mWeeksViewPager.setAdapter(screenSlidePagerAdapter);
		mWeeksViewPager.setCurrentItem(CalendarUtils.CURRENT_PAGENO);
		mWeeksViewPager
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

					@Override
					public void onPageSelected(int position) {
						Calendar cl = CalendarUtils
								.getInstance()
								.getSelectedWeekOfYear(
										position,
										(Calendar) ((CalendarActivity) getActivity())
												.getCurrentWeekCalendar()
												.clone());
						((CalendarActivity) getActivity()).getDateTv().setText(
								cl.get(Calendar.YEAR) + "年 " + "第("
										+ cl.get(Calendar.WEEK_OF_YEAR) + ")周");

						/**同步月fragment和日fragment**/
						((CalendarActivity) getActivity())
								.setCurrentMonthCalendar((Calendar) cl.clone());
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
		/**同步月fragment和日fragment**/
		((CalendarActivity) getActivity())
				.setCurrentMonthCalendar((Calendar) ((CalendarActivity) getActivity())
						.getCurrentWeekCalendar().clone());
		((CalendarActivity) getActivity())
				.setCurrentDayCalendar((Calendar) ((CalendarActivity) getActivity())
						.getCurrentWeekCalendar().clone());
	}

	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return WeekFragment.create(position);
		}

		@Override
		public int getCount() {
			return 1000;
		}
	}
}
