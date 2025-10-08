package ru.myteam.taskmanager;

public interface ITaskManager {

    void newTask(int aTaskID, Task aTask);

    void deleteAllTask();

    void deleteByKey(Integer aTaskID);

    void updateTask(Integer aTaskID, Task task);

    Task getTask(Integer aTaskID);
}
