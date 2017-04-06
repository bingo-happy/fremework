package com.fremework.bingo.bingorxretrofitfremework.Utils.Logger;

import android.os.Environment;
import android.util.Log;

import com.fremework.bingo.bingorxretrofitfremework.Utils.Logger.BingoLog.InterfaceBingoLog;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Created by bingo on 2017/3/28.
 */

public class LocalLog implements InterfaceBingoLog {

    private final String LOG_NAME = "/bingo/local.log";
    private Logger logger;
    private static CrashHandler catchHandler;
    private static LogConfigurator logConfigurator;

    @Override
    public void log(String Tag, int level, Object... msg) {
        if(logConfigurator==null){
            logConfigurator = new LogConfigurator();
            String fileName = Environment.getExternalStorageDirectory()
                    + File.separator + LOG_NAME;//设置文件名
            logConfigurator.setFileName(fileName);
            //设置root日志输出级别 默认为DEBUG
            logConfigurator.setRootLevel(Level.DEBUG);
            // 设置日志输出级别
            logConfigurator.setLevel("org.apache", Level.ERROR);
            //设置 输出到日志文件的文字格式 默认 %d %-5p [%c{2}]-[%L] %m%n
            logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
            //设置输出到控制台的文字格式 默认%m%n
            logConfigurator.setLogCatPattern("%m%n");
            //设置总文件大小
            logConfigurator.setMaxFileSize(1024 * 1024 * 5);
            //设置最大产生的文件个数
            logConfigurator.setMaxBackupSize(5);
            //设置所有消息是否被立刻输出 默认为true,false 不输出
            logConfigurator.setImmediateFlush(true);
            //是否本地控制台打印输出 默认为true ，false不输出
            logConfigurator.setUseLogCatAppender(true);
            //设置是否启用文件附加,默认为true。false为覆盖文件
            logConfigurator.setUseFileAppender(true);
            //设置是否重置配置文件，默认为true
            logConfigurator.setResetConfiguration(true);
            //是否显示内部初始化日志,默认为false
            logConfigurator.setInternalDebugging(false);
            logConfigurator.configure();
            logger = Logger.getLogger(Tag);
        }
        if (catchHandler == null) {
            catchHandler = CrashHandler.getInstance();
            catchHandler.init(logger);
        }
        String message;
        if (msg != null && msg.length == 1) {
            if(msg[0].getClass().isInstance(Throwable.class))
                message = Log.getStackTraceString((Throwable)msg[0]);
            else message = msg[0].toString();
        } else {
            StringBuilder sb = new StringBuilder();
            if (msg != null) for (Object m : msg) {
                if(m.getClass().isInstance(Throwable.class))
                    sb.append("\n").append(Log.getStackTraceString((Throwable)m));
                else sb.append(m);
            }
            message = sb.toString();
        }
        logger.error(message);

    }

    public static class CrashHandler implements Thread.UncaughtExceptionHandler {

        //系统默认的UncaughtException处理类
        private Thread.UncaughtExceptionHandler mDefaultHandler;
        //CrashHandler实例
        private static CrashHandler instance;
        private Logger gLogger;

        //保证只有一个CrashHandler实例

        private CrashHandler() {
        }

        // 获取CrashHandler实例 ,单例模式
        public static CrashHandler getInstance() {
            if (instance == null)
                instance = new CrashHandler();
            return instance;
        }

        //初始化
        public void init(Logger logger) {
            gLogger = logger;
            //设置该CrashHandler为程序的默认处理器
            Thread.setDefaultUncaughtExceptionHandler(this);
            //获取系统默认的UncaughtException处理器
            mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        }

        //当UncaughtException发生时会转入该函数来处理
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            if (mDefaultHandler != null) {
                gLogger.debug("UncaughtException--:" + ex.getMessage());
                //退出程序
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                //如果用户没有处理则让系统默认的异常处理器来处理
                mDefaultHandler.uncaughtException(thread, ex);
            }
        }
    }
}





