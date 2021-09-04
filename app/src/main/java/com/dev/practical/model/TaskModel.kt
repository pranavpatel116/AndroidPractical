package com.dev.practical.model

class TaskModel {
    var taskId : String = ""
    var taskTitle : String = ""
    var taskDescription : String = ""
    var remindMeDate : String = ""
    var dueDate : String = ""

    constructor(
        taskId: String,
        taskTitle: String,
        taskDescription: String,
        remindMeDate: String,
        dueDate: String
    ) {
        this.taskId = taskId
        this.taskTitle = taskTitle
        this.taskDescription = taskDescription
        this.remindMeDate = remindMeDate
        this.dueDate = dueDate
    }
}