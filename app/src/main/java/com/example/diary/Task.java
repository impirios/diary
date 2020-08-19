package com.example.diary;

import java.util.Date;

public class Task {
    int TaskId;
    String TaskName;
    Boolean isCompleted;

    public date getDate() {
        return Date;
    }

    public void setDate(date date) {
        Date = date;
    }

    date Date;

    public Task(int taskId, String taskName, int isCompletedint, date date) {
        TaskId = taskId;
        TaskName = taskName;
        if (isCompletedint == 0)
        {
            isCompleted = false;
        }
        else
        {
            isCompleted = true;
        }
        Date = date;
    }

    public int getTaskId() {
        return TaskId;
    }


    public void setTaskId(int taskId) {
        TaskId = taskId;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }


}
