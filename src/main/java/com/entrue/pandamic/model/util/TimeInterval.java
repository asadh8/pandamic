package com.entrue.pandamic.model.util;

import java.util.Date;

/**
 * @author asadhsheriff
 */
public class TimeInterval {

    private Date startTime;

    private Date endTime;

    public TimeInterval() {

    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "TimeInterval{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
