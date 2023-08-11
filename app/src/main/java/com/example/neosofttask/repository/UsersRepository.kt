package com.example.neosofttask.repository

import com.example.neosofttask.model.UsersData
import com.example.neosofttask.room_db.AppDatabase
import javax.inject.Inject

class UsersRepository @Inject constructor(private val database: AppDatabase) {

    suspend fun insertRegistrationDetails(usersData: UsersData): Long = database.usersDao().insertRegistrationDetails(usersData)

    suspend fun updateInfoDetails(education: String,
                                  yearOfPassing: String,
                                  grade: String,
                                  experience: String,
                                  designation:String,
                                  domain: String,
                                  uniqueId: String) = database.usersDao().updateInfoDetails(education,
                                                                        yearOfPassing,grade,experience,designation,domain,uniqueId)

    suspend fun updateAddressDetails(address: String,
                                  landmark: String,
                                  city: String,
                                  state: String,
                                  pinCode:String,
                                  uniqueId: String) = database.usersDao().updateAddressDetails(address,
        landmark,city,state,pinCode,uniqueId)

}