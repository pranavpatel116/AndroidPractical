package com.dev.practical.extra

import com.dev.practical.BuildConfig


interface Keys {
    companion object {
        const val REQUEST_CAMERA: Int = 456
        const val SELECT_FILE = 789

        // FOR USERS TABLE
        const val firebaseUsers : String = "users"
        const val firebaseUserId : String = "user_id"
        const val firebaseUserFullName : String = "full_name"
        const val firebaseUserProfilePic : String = "profile_pic"
        const val firebaseUserEmail : String = "email"

        // COMMON KEYS WHICH USED IN FIREBASE DATABASE
        const val firebaseTasks : String = "tasks"
        const val firebaseTaskId : String = "task_id"
        const val firebaseTaskTitle: String = "task_title"
        const val firebaseTaskDescription : String = "task_description"
        const val firebaseRemindMeDate : String = "remind_date"
        const val firebaseDueDate : String = "due_date"

    }
}