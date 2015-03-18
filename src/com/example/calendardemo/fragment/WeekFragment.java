package com.example.calendardemo.fragment;

import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.calendardemo.CalendarActivity;
import com.example.calendardemo.R;
import com.example.calendardemo.bean.CalendarBean;
import com.example.calendardemo.util.CalendarUtils;

public class WeekFragment extends Fragment {
	public static final String ARG_PAGE = "page";
	private int mPageNumber;
	private ListView mListView;
	private Calendar mCalendar;
	// private int[] mWeekDays;
	private List<CalendarBean> mDayOfWeek;
	// BaseGsonService实例

	public static Fragment create(int pageNumber) {
		WeekFragment fragment = new WeekFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE, pageNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPageNumber = getArguments().getInt(ARG_PAGE);
		mCalendar = CalendarUtils.getInstance().getSelectedWeekOfYear(
				mPageNumber,
				(Calendar) ((CalendarActivity) getActivity())
						.getCurrentWeekCalendar().clone());
		// mCalendar = CalendarUtils.getInstance().getSelectedWeekOfYear(
		// mPageNumber);
		mDayOfWeek = CalendarUtils.getInstance().getDaysOfWeek(mCalendar,
				mCalendar.get(Calendar.WEEK_OF_YEAR));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mListView = (ListView) inflater.inflate(R.layout.fragment_week, null);
		mListView.setAdapter(new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				TextView tv = new TextView(getActivity());
				tv.setBackgroundResource(R.drawable.no_bg);
				tv.setPadding(20, 10, 10, 10);
				tv.setText(CalendarUtils.getInstance().getWeekday(
						mDayOfWeek.get(position).day_of_week)
						+ "\n"
						+ ((mDayOfWeek.get(position).month_of_year + 1) < 10 ? "0"
								+ (mDayOfWeek.get(position).month_of_year + 1)
								: (mDayOfWeek.get(position).month_of_year + 1))
						+ "-"
						+ (mDayOfWeek.get(position).day_of_month < 10 ? "0"
								+ mDayOfWeek.get(position).day_of_month
								: mDayOfWeek.get(position).day_of_month));
				return tv;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return mDayOfWeek.get(position);
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mDayOfWeek.size();
			}
		});
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

			}
		});
		initValues();
		initTag();
		return mListView;
	}

	private void initValues() {
	}

	private void initTag() {
	}
}
