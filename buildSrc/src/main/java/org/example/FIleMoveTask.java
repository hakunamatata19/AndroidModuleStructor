package org.example;

import org.gradle.api.Action;
import org.gradle.api.DefaultTask;
import org.gradle.api.Task;

public class FIleMoveTask extends DefaultTask {
    @Override
    public Task doLast(Action<? super Task> action) {
        return super.doLast(action);
    }
}
