package com.example.neosofttask.room_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.neosofttask.model.UsersData

@Dao
interface UsersDao {

    @Insert
    suspend fun insertRegistrationDetails(usersData: UsersData): Long

    @Query("UPDATE UsersData SET education = :education,yearofpassing = :yearOfPassing,grade =:grade,experience =:experience,designation =:designation,domain =:domain WHERE id =:uniqueId")
    suspend fun updateInfoDetails(education: String,
                                  yearOfPassing: String,
                                  grade: String,
                                  experience: String,
                                  designation: String,
                                  domain: String,
                                  uniqueId: String)

    @Query("UPDATE UsersData SET address = :address,landmark = :landmark,city =:city,state =:state,pincode =:pinCode WHERE id =:uniqueId")
    suspend fun updateAddressDetails(address: String,
                                  landmark: String,
                                  city: String,
                                  state: String,
                                  pinCode: String,
                                  uniqueId: String)

}