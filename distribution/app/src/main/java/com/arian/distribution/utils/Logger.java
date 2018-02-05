package com.arian.distribution.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.List;

public class Logger {
	
	/**
	 * Log level
	 */
	public static final int LEVEL_VERBOSE = 5;
	
	public static final int LEVEL_DEBUG = 4;
	
	public static final int LEVEL_INFO = 3;
	
	public static final int LEVEL_WARN = 2;
	
	public static final int LEVEL_ERROR = 1;
	
	public static final int LEVEL_NONE = 0;
		
	//current log level
	private static int Level = LEVEL_VERBOSE;
	
	/** 默认日志输出时的TAG */
	private static String mTag = "arian";
	
	/** 用于记时的变量 */
	private static long mTimestamp = 0;
	
	
	private Logger(){
		
	}
	
	public static void v(String tag, String msg) {
		if (Level>=LEVEL_VERBOSE) {
			Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (Level>=LEVEL_DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (Level>=LEVEL_INFO) {
			Log.i(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (Level>=LEVEL_WARN) {
			Log.w(tag, msg);
		}
	}
	
	public static void e(String tag, String msg) {
		if (Level>=LEVEL_ERROR) {
			Log.e(tag, msg);
		}
	}
	
	/*默认Tag日志输出*/
	public static void e(String msg) {
		if (Level>=LEVEL_ERROR) {
			Log.e(mTag, msg);
		}
	}
	
	public static void v(String msg) {
		if (Level>=LEVEL_VERBOSE) {
			Log.v(mTag, msg);
		}
	}

	public static void d(String msg) {
		if (Level>=LEVEL_DEBUG) {
			Log.d(mTag, msg);
		}
	}

	public static void i(String msg) {
		if (Level>=LEVEL_INFO) {
			Log.i(mTag, msg);
		}
	}

	public static void w(String msg) {
		if (Level>=LEVEL_WARN) {
			Log.w(mTag, msg);
		}
	}
	
	/** 以级别为 e 的形式输出Throwable */
	public static void e(Throwable tr) {
		if (Level >= LEVEL_ERROR) {
			Log.e(mTag, "", tr);
		}
	}

	/** 以级别为 e 的形式输出LOG信息和Throwable */
	public static void e(String msg, Throwable tr) {
		if (Level >= LEVEL_ERROR && null != msg) {
			Log.e(mTag, msg, tr);
		}
	}
	
	/** 以级别为 e 的形式输出LOG信息和Throwable */
	public static void e(String tag,String msg, Throwable tr) {
		if (Level >= LEVEL_ERROR && null != msg) {
			Log.e(tag, msg, tr);
		}
	}
	
	/**
	 * 以级别为 e 的形式输出msg信息,附带时间戳，用于输出一个时间段起始点
	 * @param msg 需要输出的msg
	 */
	public static void msgStartTime(String msg) {
		mTimestamp = System.currentTimeMillis();
		if (!TextUtils.isEmpty(msg)) {
			e("[Started：" + mTimestamp + "]" + msg);
		}
	}

	/**
	 * 以级别为 e 的形式输出msg信息,附带时间戳，用于输出一个时间段结束点
	 * @param msg
	 */
	public static void elapsed(String msg) {
		long currentTime = System.currentTimeMillis();
		long elapsedTime = currentTime - mTimestamp;
		mTimestamp = currentTime;
		e("[Elapsed：" + elapsedTime + "]" + msg);
	}

	public static <T> void printList(List<T> list) {
		if (list == null || list.size() < 1) {
			return;
		}
		int size = list.size();
		i("---print List begin---");
		for (int i = 0; i < size; i++) {
			i(i + ":" + list.get(i).toString());
		}
		i("---end---");
	}

	public static <T> void printArray(T[] array) {
		if (array == null || array.length < 1) {
			return;
		}
		int length = array.length;
		i("---print Array begin---");
		for (int i = 0; i < length; i++) {
			i(i + ":" + array[i].toString());
		}
		i("---end---");
	}
	//设置日志级别
	public static void setDebugMode(int level,String secret){
		
		if("uqi".equals(secret)){
			if(level>=LEVEL_ERROR && level<=LEVEL_VERBOSE){
				Level = level;
			}else{
				Log.e(mTag, "Logger level value invalid!");
			}			
		}
	}
}
