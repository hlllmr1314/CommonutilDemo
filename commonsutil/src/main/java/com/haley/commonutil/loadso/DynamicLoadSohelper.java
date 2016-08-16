package com.haley.commonutil.loadso;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.haley.commonutil.CommonSDK;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Pattern;

/**
 * 动态加载so
 * Created by huanglei on 8/15/16.
 */
public class DynamicLoadSohelper {

    private static final String TAG = "DynamicLoadSohelper";

    /**
     * 动态记载so
     * @param path so的本地路径
     * @return
     */
    public static int loadSo(String path) {



        try {
            Context context = CommonSDK.INSTANCE.getContext();
            Pattern mPatterns = Pattern.compile("/");
            String localPath = Environment.getExternalStorageDirectory() + path;
            Log.v(TAG, "LazyBandingLib localPath:" + localPath);

            String[] tokens = mPatterns.split(path);
            if (null == tokens || tokens.length <= 0
                    || tokens[tokens.length - 1] == "") {
                Log.v(TAG, "非法的文件路径！");
                return -3;
            }

            // 开辟一个输入流
            File inFile = new File(localPath);

            // 判断需加载的文件是否存在
            if (!inFile.exists()) {
                // 下载远程驱动文件
                Log.v(TAG, inFile.getAbsolutePath() + " is not fond!");
                return 1;
            }

            FileInputStream fis = new FileInputStream(inFile);
            File dir = context.getDir("libs", Context.MODE_PRIVATE);
            // 获取驱动文件输出流
            File soFile = new File(dir, tokens[tokens.length - 1]);
            if (!soFile.exists()) {
                Log.v(TAG, "### " + soFile.getAbsolutePath() + " is not exists");
                FileOutputStream fos = new FileOutputStream(soFile);
                Log.v(TAG, "FileOutputStream:" + fos.toString() + ",tokens:"
                        + tokens[tokens.length - 1]);
                // 字节数组输出流，写入到内存中(ram)
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = fis.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                // 从内存到写入到具体文件
                fos.write(baos.toByteArray());
                // 关闭文件流
                baos.close();
                fos.close();
            }
            fis.close();

            Log.v(TAG, "### System.load start");
            // 加载外设驱动
            System.load(soFile.getAbsolutePath());
            Log.v(TAG, "### System.load End");
            return 0;
        } catch (Exception e) {
            Log.v(TAG, "Exception   " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

}
