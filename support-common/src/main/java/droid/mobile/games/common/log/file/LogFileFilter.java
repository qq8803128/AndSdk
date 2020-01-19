package droid.mobile.games.common.log.file;

import droid.mobile.games.common.log.LogLevel;

/**
 * Created by pengwei on 2017/3/31.
 * 日志过滤器
 */

public interface LogFileFilter {
    boolean accept(@LogLevel.LogLevelType int level, String tag, String logContent);
}