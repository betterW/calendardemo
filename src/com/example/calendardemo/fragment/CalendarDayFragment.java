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

public class CalendarDayFragment extends Fragment {
	private ViewPager mDaysViewPager;

	public static CalendarDayFragment newInstance() {
		CalendarDayFragment details = new CalendarDayFragment();
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
		mDaysViewPager = (ViewPager) view.findViewById(R.id.calendar_weeks_vp);
		final ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(
				getActivity().getSupportFragmentManager());
		mDaysViewPager.setAdapter(screenSlidePagerAdapter);
		mDaysViewPager.setCurrentItem(CalendarUtils.CURRENT_PAGENO);
		mDaysViewPager
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

					@Override
					public void onPageSelected(int position) {
						Calendar cl = CalendarUtils
								.getInstance()
								.getSelectedDayOfMonth(
										position,
										(Calendar) ((CalendarActivity) getActivity())
												.getCurrentDayCalendar()
												.clone());
						((CalendarActivity) getActivity())
								.getDateTv()
								.setText(
										cl.get(Calendar.YEAR)
												+ "年"
												+ (cl.get(Calendar.MONTH) + 1)
												+ "月"
												+ cl.get(Calendar.DAY_OF_MONTH)
												+ "日 "
												+ CalendarUtils
														.getInstance()
														.getWeekday(
																cl.get(Calendar.DAY_OF_WEEK)));

						/**同步月fragment和周fragment**/
						((CalendarActivity) getActivity())
								.setCurrentWeekCalendar((Calendar) cl.clone());
						((CalendarActivity) getActivity())
								.setCurrentMonthCalendar((Calendar) cl.clone());
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
		/**同步月fragment和周fragment**/
		((CalendarActivity) getActivity())
				.setCurrentWeekCalendar((Calendar) ((CalendarActivity) getActivity())
						.getCurrentDayCalendar().clone());
		((CalendarActivity) getActivity())
				.setCurrentMonthCalendar((Calendar) ((CalendarActivity) getActivity())
						.getCurrentDayCalendar().clone());
	}

	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return DayFragment.create(position);
		}

		@Override
		public int getCount() {
			return 1000;
		}
	}
}
