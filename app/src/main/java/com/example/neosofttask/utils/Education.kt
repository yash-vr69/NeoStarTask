package com.example.neosofttask.utils

enum class Education {
    Select,
    Post_Graduation,
    Graduation,
    HSC,
    Diploma,
    SSC;

    companion object {
        fun list(): ArrayList<Education> {
            return arrayListOf(Select, Post_Graduation, Graduation, HSC, Diploma, SSC)
        }
    }

}