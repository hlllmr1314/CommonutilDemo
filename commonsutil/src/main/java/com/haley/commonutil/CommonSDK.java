package com.haley.commonutil;

import android.content.Context;

/**
 * Created by huanglei on 8/15/16.
 */
public class CommonSDK {

    private static CommonSDK instance;
    public static CommonSDK INSTANCE = newInstance();

    private CommonSDKInterface sdkInterface;
    private boolean hasInit;

    private CommonSDK() {

    }

    private static CommonSDK newInstance() {
        if (instance == null) {
            instance = new CommonSDK();
        }
        return instance;
    }

    /**
     * 初始化commonsdk
     *
     * @param sdkInterface
     */
    public void init(CommonSDKInterface sdkInterface) {
        if (sdkInterface == null) {
            throw new RuntimeException("sdkInterface can not null");
        }
        this.sdkInterface = sdkInterface;
        this.hasInit = true;
    }

    /**
     * 检查是否进行了初始化
     *
     * @return
     */
    public boolean checkInt() {
        return hasInit;
    }

    /**
     * 获取上下文
     *
     * @return
     */
    public Context getContext() {
        if (hasInit) {
            return sdkInterface.getContext();
        } else {
            throw new RuntimeException("sdk must cal init() method");
        }
    }

    /**
     * 获取应用名字
     *
     * @return
     */
    public String getAppName() {
        if (hasInit) {
            return getContext().getPackageName();
        } else {
            throw new RuntimeException("sdk must cal init() method");
        }
    }

    /**
     * 注销commonsdk
     */
    public void destory() {
        sdkInterface = null;
        this.hasInit = false;
    }

}
