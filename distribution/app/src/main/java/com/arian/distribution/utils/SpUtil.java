package com.arian.distribution.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
	private static SharedPreferences sp;

	public static SharedPreferences getInstance(Context ctx) {
		if (sp == null) {
			sp = ctx.getSharedPreferences("config", Context.MODE_APPEND);
		}
		return sp;
	}

	public static void putBoolean(Context ctx, String key, boolean value) {
		if (sp == null) {
			sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		sp.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context ctx, String key, boolean defValue) {
		if (sp == null) {
			sp = ctx.getSharedPreferences("config", Context.MODE_APPEND);
		}
		return sp.getBoolean(key, defValue);
	}

	public static void putString(Context ctx, String key, String value) {
		if (sp == null) {
			sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		sp.edit().putString(key, value).commit();
	}

	public static String getString(Context ctx, String key, String defValue) {
		if (sp == null) {
			sp = ctx.getSharedPreferences("config", Context.MODE_APPEND);
		}
		return sp.getString(key, defValue);
	}

	public static void putLong(Context ctx, String key, long value) {
		if (sp == null) {
			sp = ctx.getSharedPreferences("config", Context.MODE_APPEND);
		}
		sp.edit().putLong(key, value).commit();
	}

	public static long getLong(Context ctx, String key, long defValue) {
		if (sp == null) {
			sp = ctx.getSharedPreferences("config", Context.MODE_APPEND);
		}
		return sp.getLong(key, defValue);
	}

	public static void putInt(Context ctx, String key, int value) {
		if (sp == null) {
			sp = ctx.getSharedPreferences("config", Context.MODE_APPEND);
		}
		sp.edit().putInt(key, value).commit();
	}

	public static int getInt(Context ctx, String key, int defValue) {
		if (sp == null) {
			sp = ctx.getSharedPreferences("config", Context.MODE_APPEND);
		}
		return sp.getInt(key, defValue);
	}

	public static void remove(Context ctx, String key) {
		if (sp == null) {
			sp = ctx.getSharedPreferences("config", Context.MODE_APPEND);
		}
		sp.edit().remove(key).commit();
	}
}
