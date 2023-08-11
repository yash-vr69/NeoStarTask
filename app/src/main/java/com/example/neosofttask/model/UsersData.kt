package com.example.neosofttask.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsersData(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val emailId: String,
    val gender: String,
    val password: String,
    val education: String?,
    val yearofpassing: String?,
    val grade: String?,
    val experience: String?,
    val designation: String?,
    val domain: String?,
    val address: String?,
    val landmark: String?,
    val city: String?,
    val state: String?,
    val pinCode: String?
    ){

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
