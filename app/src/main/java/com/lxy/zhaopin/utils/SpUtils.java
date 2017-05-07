package com.lxy.zhaopin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Set;

/**
 *@author cifz
 *首选项的工具类
 * boolean String
 */
public final class SpUtils {
	
	private final static String name = "newbike_config";
	private final static int mode = Context.MODE_PRIVATE;
	private final static String[] keys = {"token","userid","jumpflag"};
	private final static String keyIsLogin = "islogin";
	

	//存值
	public final static void putBoolean(Context context,String key,boolean value){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		Editor edit = sp.edit();
		edit.putBoolean(key, value);
		edit.commit();
	}
	
	public final static void putString(Context context,String key,String value){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		Editor edit = sp.edit();
		edit.putString(key, value);
		edit.commit();
	}

	public final static void putStringList(Context context, String key, Set<String> searchList){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		Editor edit = sp.edit();
		edit.putStringSet(key,searchList);
		edit.apply();
	}

	//获取值
	public final static Set<String> getStringList(Context context, String key){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		return sp.getStringSet(key,null) ;
	}

	//获取值
	public final static boolean getBoolean(Context context,String key,boolean defValue){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		return sp.getBoolean(key, defValue);
	}
	
	public final static String getString(Context context,String key,String defValue){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		return sp.getString(key, defValue);
	}

   //退出登录
	public final static void logOff(Context context){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		Editor edit = sp.edit();
		for (String key:keys) {
			edit.putString(key, "");
		}
		edit.putBoolean(keyIsLogin, false);
		edit.commit();
	}
	
	
	
}
