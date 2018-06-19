package com.mmm.comic.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.mmm.comic.R;

@SuppressLint("InflateParams")
public class ChooseDialog {
	private Activity context;
	public Dialog dialog;
	private Display display;
	private OnClickDialogListenerSure onClickDialogListenerSure;
	private TextView wechat_tv;
	private TextView alipay_tv;
	private TextView cancel_tv;

	public ChooseDialog(Activity context) {
		this.context = context;
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
	}

	@SuppressWarnings("deprecation")
	public ChooseDialog builder() {
		View view = LayoutInflater.from(context).inflate(R.layout.dialog_choosepic, null);
		wechat_tv= (TextView) view.findViewById(R.id.wechat_tv);
		alipay_tv= (TextView) view.findViewById(R.id.alipay_tv);
		cancel_tv= (TextView) view.findViewById(R.id.cancel_tv);

		dialog = new Dialog(context, R.style.ChooseDialogStyle);
		dialog.setContentView(view);
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//		lp.dimAmount=0.4f;
		lp.width = ViewGroup.LayoutParams.MATCH_PARENT; // 宽度
//		lp.height = (int) (display.getHeight() * 0.6); // 高度
		lp.gravity = Gravity.CENTER;
		dialog.getWindow().setAttributes(lp);
		initEvent();
		return this;
	}

	public ChooseDialog setCancelable(boolean cancel) {
		dialog.setCancelable(cancel);
		return this;
	}

	public ChooseDialog setCanceledOnTouchOutside(boolean cancel) {
		dialog.setCanceledOnTouchOutside(cancel);
		return this;
	}

	private void initEvent() {
		wechat_tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickDialogListenerSure.onClick(v.getId());
			}
		});
		alipay_tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickDialogListenerSure.onClick(v.getId());
			}
		});
		cancel_tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.hide();
				//onClickDialogListenerSure.onClick(v.getId());
			}
		});

	}

	public void setOnClickListenerSure(OnClickDialogListenerSure onClickDialogListenerSure) {
		this.onClickDialogListenerSure = onClickDialogListenerSure;
	}

	public interface OnClickDialogListenerSure {
		public void onClick(int id);
	}

	public void show() {
		dialog.show();
	}
	public void hide() {
		dialog.hide();
	}
}
