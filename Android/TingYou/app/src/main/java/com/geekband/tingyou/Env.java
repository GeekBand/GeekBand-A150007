package com.geekband.tingyou;

/**
 * Created by Travis on 9/8/15.
 * This class is for storing the environmental parameters
 */
public class Env {
    public static final boolean DEV_MODE = false;

    public static final int SQLITE_VERSION = 17;

    public static final boolean DOWNLOAD_ONLY_UNDER_WIFI = true;

    public static final String SQLITE_SECRET_KEY = "";

    //release URL

    public static final String BASE_URL = "";

    public static String ROOT_DIR;

    public static boolean ALREADY_LOG_IN = false;

    //Database constant
    public static final String KEY_ID = "_id";
    public static final String KEY_PROVINCE_COLUMN = "PROVINCE_COLUMN";
    public static final String KEY_CITY_COLUMN = "CITY_COLUMN";
    public static final String KEY_SCENE_COLUMN = "SCENE_COLUMN";
    public static final String KEY_LEVEL_COLUMN = "LEVEL_COLUMN";
    public static final String KEY_VOICE_COLUMN = "VOICE_COLUMN";
    public static final String KEY_KNOWLEDGE_COLUMN = "KNOWLEDGE_COLUMN";
    public static final String KEY_BOUGHT_COLUMN = "BOUGHT_COLUMN";
    public static final String KEY_IS_PROVINCE = "IS_PROVINCE_COLUMN";
}
