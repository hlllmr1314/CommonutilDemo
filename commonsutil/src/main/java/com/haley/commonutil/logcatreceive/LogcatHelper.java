package com.haley.commonutil.logcatreceive;

import android.text.TextUtils;
import android.util.Log;

import com.haley.commonutil.CommonSDK;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by huanglei on 8/14/16.
 */
public class LogcatHelper {

    public static String defLogFileDir = "/sdcard/log/";
    public static String defLogFileName = "logout.txt";

    public static void saveLogcat() {
        saveLogcat(defLogFileDir + CommonSDK.INSTANCE.getAppName() + File.separator + String.valueOf(System.currentTimeMillis()) + defLogFileName);
    }

    /**
     * 保存当前应用的日志信息（如果是root会保存前后所有的应用log 否则只保存当前用的log）
     *
     * @param savePath 要保存的日志文件全路径
     */
    public static void saveLogcat(String savePath) {

        if (TextUtils.isEmpty(savePath)) {
            throw new RuntimeException("savePath can not empty");
        }

        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            savePathFile.mkdirs();
        }

        boolean hasRoot = ShellUtils.checkRootPermission();
        if (hasRoot) {
            ArrayList commnandList = new ArrayList();
            commnandList.add(savePath);
            ShellUtils.execCommand(commnandList, true);
        } else {
            try {
                ArrayList<String> commandLine = new ArrayList();
                commandLine.add("logcat");
                commandLine.add("-d");
                commandLine.add("-v");
                commandLine.add("time");
                commandLine.add("-f");
                commandLine.add(savePath);
                Runtime.getRuntime().exec(commandLine.toArray(new String[commandLine.size()]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
