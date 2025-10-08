package ru.myteam.taskmanager;

public class Task {
    private final int taskID;
    private final String taskName;
    private final String description;
    private final TaskStatus status;
    protected TaskType taskType;

    public TaskType getTaskType() {
        return taskType;
    }

    public int getTaskID() {
        return taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Task(int aTaskID, String aTaskName, String aDescription, TaskStatus aStatus){
        taskID = aTaskID;
        taskName = aTaskName;
        description = aDescription;
        status = aStatus;
        taskType = TaskType.TASK;
    }

    protected String getToString(){
        return    "    taskID       = " + taskID + ",\n"
                + "    taskName     = " + taskName + ",\n"
                + "    description  = " + description + ",\n"
                + "    status       = " + status + ",\n"
                + "    taskType     = " + taskType;
    }

    @Override
    public String toString(){
        return getClass() + " {\n"
                + getToString()+"\n"
                + "}";
    }

    public String getTypeTaskName(){
         switch (this.taskType){
             case TASK:
                return "Задача";
             case SUB_TASK:
                return "Подзадача";
            case EPIC:
                return "Эпик";
            default:
                return "";

        }
    }

}
