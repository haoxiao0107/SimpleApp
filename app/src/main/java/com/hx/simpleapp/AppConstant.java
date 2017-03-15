package com.hx.simpleapp;

import android.os.Environment;

import java.io.File;


public class AppConstant {

    //DATA STORE
    public static final String DATA_PATH = AppContext.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "fastapp";
    public static final String LOG_PATH = SDCARD_PATH + File.separator + "log" + File.separator;
    public static final String NET_DATA_PATH = DATA_PATH + File.separator + "net_cache";
    public static final String UPDATE_FILE_PATH = SDCARD_PATH + File.separator + "update";


    //SP CONFIG
    public static final String KEY_MODE_NO_IMAGE = "no image";
    public static final String KEY_MODE_AUTO_CACHE = "auto cache";

    //DB NAME
    public static final String DB_NAME = "simpleapp.realm";

    //天行数据  HOST URL
    public static final String API_WX_URL = "http://api.tianapi.com";
    public static final String KEY_WX = "1466d12589f62b020dbd7bb98c5c82d9";

    //干货集中营
    public static final String API_GAN_URL = "http://gank.io/api/";

    //fir 内测托管平台
    public static final String API_FIR_URL = "http://api.fir.im";
    public static final String TOKEN_FIR = "b02e65fcfe2868383c9b035d271b599c";
    public static final String KEY_APP_ID = "58b90a6aca87a87a770003fe";

    //BugHD  崩溃分析
    public static final String GENERAL_KEY = "e5b8e6ab9b43574eb076469d6c9237f6";

}
