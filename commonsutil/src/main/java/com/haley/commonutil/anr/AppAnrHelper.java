package com.haley.commonutil.anr;

import android.os.FileObserver;
import android.util.Log;

/**
 * 用于监听程序anr，然后做一些处理
 * <p/>
 * Created by huanglei on 8/14/16.
 */
public class AppAnrHelper extends FileObserver {

    /*
    ACCESS ： 即文件被访问
    MODIFY ： 文件被修改
    ATTRIB ： 文件属性被修改，如 chmod、chown、touch 等
    CLOSE_WRITE ： 可写文件被 close
    CLOSE_NOWRITE ： 不可写文件被 close
    OPEN ： 文件被 open
    MOVED_FROM ： 文件被移走，如 mv
    MOVED_TO ： 文件被移来，如 mv、cp
    CREATE ： 创建新文件
    DELETE ： 文件被删除，如 rm
    DELETE_SELF ： 自删除，即一个可执行文件在执行时删除自己
    MOVE_SELF ： 自移动，即一个可执行文件在执行时移动自己
    CLOSE ： 文件被关闭，等同于(IN_CLOSE_WRITE | IN_CLOSE_NOWRITE)
    ALL_EVENTS ： 包括上面的所有事件
     */

    private AnrListener listener;

    public AppAnrHelper(String path, AnrListener listener) {
        super(path);
        this.listener = listener;
    }

    public AppAnrHelper(String path, int mask, AnrListener listener) {
        super(path, mask);
        this.listener = listener;
    }

    @Override
    public void onEvent(int event, String path) {
        switch (event) {
            case ACCESS:
                Log.d("ACCESS", "path:" + path);
                break;
            case MODIFY:
                Log.d("MODIFY", "path:" + path);
                break;
            case ATTRIB:
                Log.d("ATTRIB", "path:" + path);
                break;
            case CLOSE_WRITE:
                Log.d("CLOSE_WRITE", "path:" + path);
                break;
            case CLOSE_NOWRITE:
                Log.d("CLOSE_NOWRITE", "path:" + path);
                break;
            case OPEN:
                Log.d("OPEN", "path:" + path);
                break;
            case MOVED_FROM:
                Log.d("MOVED_FROM", "path:" + path);
                break;
            case MOVED_TO:
                Log.d("MOVED_TO", "path:" + path);
                break;
            case CREATE:
                Log.d("CREATE", "path:" + path);
                listener.onAnr();
                break;
            case DELETE:
                Log.d("DELETE", "path:" + path);
                break;
            case DELETE_SELF:
                Log.d("DELETE_SELF", "path:" + path);
                break;
            case MOVE_SELF:
                Log.d("MOVE_SELF", "path:" + path);
                break;
            case ALL_EVENTS:
                Log.d("ALL_EVENTS", "path:" + path);
                break;
        }
    }

}
