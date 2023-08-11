package com.example.neosofttask.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.neosofttask.model.UsersData

@Database(entities = [UsersData::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun usersDao(): UsersDao

}