package com.example.administrator.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.myapplication.R;


/**
 * 头部
 */
public class HeaderView extends LinearLayout {
	/** 刷新状态 */
	private LoadState mState = LoadState.NORMAL;

	private View mHeader = null;
	private ImageView mArrow = null;
	private ProgressBar mProgressBar = null;
	private TextView mRefreshTips = null;
	private TextView mRefreshLastTime = null;
	private RotateAnimation mRotateUp = null;
	private RotateAnimation mRotateDown = null;
	private final static int ROTATE_DURATION = 250;

	/** 一分钟的毫秒值，用于判断上次的更新时间. */
	private final long ONE_MINUTE = 60 * 1000;
	/** 一小时的毫秒值，用于判断上次的更新时间. */
	private final long ONE_HOUR = 60 * ONE_MINUTE;
	/** 一天的毫秒值，用于判断上次的更新时间. */
	private final long ONE_DAY = 24 * ONE_HOUR;
	/** 一月的毫秒值，用于判断上次的更新时间. */
	private final long ONE_MONTH = 30 * ONE_DAY;
	/** 一年的毫秒值，用于判断上次的更新时间. */
	private final long ONE_YEAR = 12 * ONE_MONTH;

	public HeaderView(Context context) {
		this(context, null);
	}

	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHeaderView(context);
	}

	private void initHeaderView(Context context) {
		LinearLayout.LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 0);
		mHeader = LayoutInflater.from(context).inflate(R.layout.g_refresh_header, null);
		addView(mHeader, lp);
		setGravity(Gravity.BOTTOM);
		mArrow = (ImageView) mHeader.findViewById(R.id.ivArrow);
		mProgressBar = (ProgressBar) mHeader.findViewById(R.id.pbWaiting);
		mRefreshTips = (TextView) mHeader.findViewById(R.id.refresh_tips);
		mRefreshLastTime = (TextView) mHeader.findViewById(R.id.refresh_last_time);

		mRotateUp = new RotateAnimation(0.0f, -180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateUp.setDuration(ROTATE_DURATION);
		mRotateUp.setFillAfter(true);

		mRotateDown = new RotateAnimation(-180.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateDown.setDuration(ROTATE_DURATION);
		mRotateDown.setFillAfter(true);
		Log.e("HeaderView","HeaderView初始化了");
	}

	public void setHeaderState(LoadState state) {
		if (mState == state) {
			return;
		}
		mArrow.clearAnimation();
		if (state == LoadState.LOADING) {
			mArrow.setVisibility(View.GONE);
			mProgressBar.setVisibility(View.VISIBLE);
		} else {
			mProgressBar.setVisibility(View.GONE);
			mArrow.setVisibility(View.VISIBLE);
		}

		switch (state) {
		case NORMAL:
			mArrow.startAnimation(mRotateDown);
			mRefreshTips.setText(R.string.g_pull_down_for_refresh);
			break;

		case WILL_RELEASE:
			//旋转当前的箭头的状态
			mArrow.startAnimation(mRotateUp);
			mRefreshTips.setText(R.string.g_release_for_refresh);
			break;

		case LOADING:
			mRefreshTips.setText(R.string.g_refreshing);
			break;

		default:
			break;
		}

		mState = state;
	}

	public LoadState getCurrentState() {
		return mState;
	}

	public void setHeaderHeight(int height) {
		if (height <= 0) {
			height = 0;
		}
		LayoutParams lp = (LayoutParams) mHeader.getLayoutParams();
		lp.height = height;
		mHeader.setLayoutParams(lp);
	}

	public int getHeaderHeight() {
		return mHeader.getHeight();
	}

	/**
	 * 刷新下拉头中上次更新时间的文字描述。
	 */
	protected void refreshUpdatedAtValue(long lastUpdateTime) {
		long currentTime = System.currentTimeMillis();
		long timePassed = currentTime - lastUpdateTime;
		long timeIntoFormat;
		String updateAtValue;
		if (lastUpdateTime == -1) {
			updateAtValue = getResources().getString(R.string.g_not_updated_yet);
		} else if (timePassed < 0) {
			updateAtValue = getResources().getString(R.string.g_time_error);
		} else if (timePassed < ONE_MINUTE) {
			updateAtValue = getResources().getString(R.string.g_updated_just_now);
		} else if (timePassed < ONE_HOUR) {
			timeIntoFormat = timePassed / ONE_MINUTE;
			String value = timeIntoFormat + "分钟";
			updateAtValue = String.format(getResources().getString(R.string.g_updated_at), value);
		} else if (timePassed < ONE_DAY) {
			timeIntoFormat = timePassed / ONE_HOUR;
			String value = timeIntoFormat + "小时";
			updateAtValue = String.format(getResources().getString(R.string.g_updated_at), value);
		} else if (timePassed < ONE_MONTH) {
			timeIntoFormat = timePassed / ONE_DAY;
			String value = timeIntoFormat + "天";
			updateAtValue = String.format(getResources().getString(R.string.g_updated_at), value);
		} else if (timePassed < ONE_YEAR) {
			timeIntoFormat = timePassed / ONE_MONTH;
			String value = timeIntoFormat + "个月";
			updateAtValue = String.format(getResources().getString(R.string.g_updated_at), value);
		} else {
			timeIntoFormat = timePassed / ONE_YEAR;
			String value = timeIntoFormat + "年";
			updateAtValue = String.format(getResources().getString(R.string.g_updated_at), value);
		}
		mRefreshLastTime.setText(updateAtValue);
	}
}
