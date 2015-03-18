package com.example.calendardemo.fragment;

import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calendardemo.CalendarActivity;
import com.example.calendardemo.R;
import com.example.calendardemo.bean.CalendarBean;
import com.example.calendardemo.util.CalendarUtils;
import com.example.calendardemo.view.MyGridLayout;
import com.example.calendardemo.view.MyGridLayout.GridAdatper;
import com.example.calendardemo.view.MyGridLayout.OnItemClickListener;

public class MonthFragment extends Fragment {
	public static final String ARG_PAGE = "page";
	private int mPageNumber;
	private MyGridLayout mGridView;
	private Calendar mCalendar;
	private List<CalendarBean> mDaysOfMonth;
	// BaseGsonService实例
	private long start;
	private long end;

	public static Fragment create(int pageNumber) {
		MonthFragment fragment = new MonthFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE, pageNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPageNumber = getArguments().getInt(ARG_PAGE);

		// mCalendar =
		// CalendarUtils.getInstance().getSelectedMonth(mPageNumber);

		mCalendar = CalendarUtils.getInstance().getSelectedMonth(
				mPageNumber,
				(Calendar) ((CalendarActivity) getActivity())
						.getCurrentMonthCalendar().clone());

		mDaysOfMonth = CalendarUtils.getInstance().getDaysOfMonth(mCalendar);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mGridView = (MyGridLayout) inflater.inflate(R.layout.fragment_month,
				null);
		mGridView.setGridAdapter(new GridAdatper() {

			/***
			 * 每个item，根据自己需求可以显示自定义tag
			 * */
			@Override
			public View getView(int position) {
				ViewGroup view = (ViewGroup) LayoutInflater.from(getActivity())
						.inflate(R.layout.grid_item_day_of_month, null);
				view.setTag(CalendarUtils.getInstance().getViewTag(
						mDaysOfMonth.get(position)));
				TextView tv1 = (TextView) view.getChildAt(0);
				TextView tv2 = (TextView) view.getChildAt(1);
				tv1.setText(mDaysOfMonth.get(position).day_of_month + "");
				tv1.setPadding(0, 0, 3, 0);
				if (!mDaysOfMonth.get(position).isCurrentMonth) {
					tv1.setTextColor(getResources().getColor(
							R.color.darker_gray));
					tv2.setTextColor(getResources().getColor(
							R.color.darker_gray));
					view.setBackgroundResource(R.drawable.invalid_bg);
				} else {
					view.setBackgroundResource(R.drawable.valid_bg);
				}
				if (mDaysOfMonth.get(position).isToday) {
					view.setBackgroundResource(R.drawable.current_bg);
				}
				// tv.setGravity(Gravity.RIGHT);
				return view;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mDaysOfMonth.size();
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return mDaysOfMonth.get(position);
			}
		});
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(Object obj, View v, int index) {
				// TODO Auto-generated method stub
				if (obj instanceof CalendarBean) {
					CalendarBean mCalendarBean = (CalendarBean) obj;
					// Toast.makeText(getActivity(), "item=" + obj, 0).show();
					((CalendarActivity) getActivity()).getCurrentDayCalendar()
							.set(Calendar.DAY_OF_MONTH,
									mCalendarBean.day_of_month);
					((CalendarActivity) getActivity()).getCurrentDayCalendar()
							.set(Calendar.MONTH,
									mCalendarBean.month_of_year - 1);
					((CalendarActivity) getActivity()).setSelectTab(2);
				}

			}
		});
		initValues();
		initData();
		return mGridView;
	}

	private void initValues() {
	}

	private void initData() {
		
	}
}
