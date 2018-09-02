package com.fpl.fop.fop.util.rx.scheduler;

/**
 */
public class SchedulerUtils {

    public static <T> IoMainScheduler<T> ioToMain() {
        return new IoMainScheduler<>();
    }
}
