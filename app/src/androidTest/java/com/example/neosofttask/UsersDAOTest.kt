package com.example.neosofttask

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.neosofttask.model.UsersData
import com.example.neosofttask.room_db.AppDatabase
import com.example.neosofttask.room_db.UsersDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersDAOTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: UsersDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = database.usersDao()
    }

    @Test
    fun insertData(){
        val registrationDetails = UsersData("test","test","9867364019","test@gmail","Male","tT@12345678",null,null,null,null,null,null,
        null,null,null,null,null)
        runBlocking {
            val id = dao.insertRegistrationDetails(registrationDetails)
            val insertedData = dao.getAllDetails(id.toString())
            Assert.assertEquals(registrationDetails,insertedData)
        }
    }

    @Test
    fun updateYourInfoData(){
        var uniqueId = ""
        var result: UsersData
        val registrationDetails = UsersData("test","test","9867364019","test@gmail","Male","tT@12345678",null,null,null,null,null,null,
            null,null,null,null,null)
        runBlocking {
            uniqueId = dao.insertRegistrationDetails(registrationDetails).toString()
            dao.updateInfoDetails("BSc IT","2019","A","2","Technical Consultant","E Commerce",uniqueId)
            result = dao.getAllDetails(uniqueId)
        }
        val yourInfoDetails = UsersData("test","test","9867364019","test@gmail","Male","tT@12345678","BSc IT","2019","A","2","Technical Consultant","E Commerce",
            null,null,null,null,null)
        Assert.assertEquals(result,yourInfoDetails)
    }

    @Test
    fun updateAddressData(){
        var uniqueId = ""
        var result: UsersData
        val yourInfoDetails = UsersData("test","test","9867364019","test@gmail","Male","tT@12345678","BSc IT","2019","A","2","Technical Consultant","E Commerce",
            null,null,null,null,null)
        runBlocking {
            uniqueId = dao.insertRegistrationDetails(yourInfoDetails).toString()
        }
        val addressDetails = UsersData("test","test","9867364019","test@gmail","Male","tT@12345678","BSc IT","2019","A","2","Technical Consultant","E Commerce",
            "Bhavani","AG link road","Mumbai","Maharashtra","400099")
        runBlocking {
            dao.updateAddressDetails("Bhavani","AG link road","Mumbai","Maharashtra","400099",uniqueId)
            result = dao.getAllDetails(uniqueId)
        }
        Assert.assertEquals(result,addressDetails)
    }

    @After
    fun tearDown() {
        database.close()
    }
}