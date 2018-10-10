package com.lukemi.android.tutorial.sessionlifecycle;

/**
 * Created by mzchen on 2016/10/23.
 */

public class RunTimeDuration {
    private long startTime;
    private long endTime;
    private long duration = endTime - startTime;
    private static RunTimeDuration runTimeDuration = new RunTimeDuration();


    private RunTimeDuration() {
    }

    public static RunTimeDuration getInstance() {
        return runTimeDuration;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "RunTimeDuration{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                '}';
    }
}
