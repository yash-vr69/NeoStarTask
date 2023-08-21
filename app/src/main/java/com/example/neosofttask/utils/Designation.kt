package com.example.neosofttask.utils

enum class Designation {

    SELECT,
    TECHNICAL_CONSULTANT,
    ANDROID_DEVELOPER,
    Jr_ANDROID_DEVELOPER,
    SENIOR_ANDROID_DEVELOPER,
    ANDROID_TEAM_LEAD,
    ANDROID_PROJECT_MANAGER;

    override fun toString(): String {
        return name.replace('_', ' ')
    }

}