package com.mmm.comic.util;

import android.widget.Toast;

import com.mmm.comic.APP;

public class ToastUtil {
	private static Toast toast;
	public static void showToast(String str) {
		if (toast == null) {
			toast = Toast.makeText(APP.application, str, Toast.LENGTH_SHORT);
		} else {
			toast.setText(str);
		}
		toast.show();
	}

}
