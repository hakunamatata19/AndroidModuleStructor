package com.coocaa;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.plugins.JavaBasePlugin;

public class ReleasePlugin implements Plugin<Project> {

    public static final String RELEASE_ENG_ROLE_PROP = "releaseEngineer";
    public static final String RELEASE_TASK_NAME = "release";

    @Override
    public void apply(Project project) {
        Object prop =project.findProperty(RELEASE_ENG_ROLE_PROP);
        project.getLogger().quiet("chenguoPlugin"+prop);
        if( prop !=null){
            Task task = project.getTasks().create(RELEASE_TASK_NAME);
            task.setGroup(JavaBasePlugin.DOCUMENTATION_GROUP);
            task.doLast(new Action<Task>() {
                @Override
                public void execute(Task task) {
                    task.getLogger().quiet("Releasing to Production....");
                }
            });
        }
    }
}
