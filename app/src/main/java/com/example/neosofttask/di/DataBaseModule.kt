package com.example.neosofttask.di

import android.content.Context
import androidx.room.Room
import com.example.neosofttask.repository.UsersRepository
import com.example.neosofttask.room_db.AppDatabase
import com.example.neosofttask.room_db.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Modules {

    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): UsersDao {
        return appDatabase.usersDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "NeoDB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideExampleRepository(dataBase: AppDatabase): UsersRepository = UsersRepository(dataBase)

}