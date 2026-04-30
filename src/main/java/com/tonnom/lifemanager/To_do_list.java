package com.tonnom.lifemanager;

public class To_do_list {
    private String task;
    private boolean done;

    public To_do_list(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    public String getTask() {
        return task;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

