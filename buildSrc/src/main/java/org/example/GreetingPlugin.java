package org.example;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.provider.Property;


class GreetingPlugin implements Plugin<Project> {
    public void apply(Project project) {
        GreetingPluginExtension extension = project.getExtensions().create("greeting",
                GreetingPluginExtension.class);
        project.task("hello", new Action<Task>() {
            @Override
            public void execute(Task task) {
                System.out.println("curentTask: "+task.getName());
            }
        });
    }

    interface GreetingPluginExtension {
        Property<String> getMessage();
        Property<String> getGreeter();
    }
}