package com.coocaa;

import org.gradle.api.Action;
import org.gradle.api.DefaultTask;
import org.gradle.api.Task;


import javax.inject.Inject;

import groovy.lang.Closure;

public class CustomTask extends DefaultTask {
    private final String message;
    private final int number;

    @Inject
    CustomTask(String msg, int num){
        this.message =msg;
        this.number = num;
    }



    @Override
    public Task doLast(Closure action) {
       getLogger().quiet("doLast:"+message);
       return  super.doLast(action);
    }

}
