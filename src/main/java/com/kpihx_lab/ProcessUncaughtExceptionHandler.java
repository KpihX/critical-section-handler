package com.kpihx_lab;

public class ProcessUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable e) {
        System.err.println("Exception in thread " + thread.getName() + ": ");
        e.printStackTrace();
        System.exit(1);
    }
}
