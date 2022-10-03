package me.jhones.guests.repository

import android.content.ContentValues
import android.content.Context
import me.jhones.guests.constants.DataBaseConstants
import me.jhones.guests.models.GuestModel
import java.lang.Exception

class GuestRepository private constructor(context: Context) {
    private val guestDataBase = GuestDataBase(context)

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val values = ContentValues().apply {
                val presence = if (guest.presence) 1 else 0
                put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
                put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)
            }

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            true
        }catch (e: Exception){
            e.printStackTrace()
            false
        }
    }

    fun update(guest: GuestModel):Boolean {
        return try {
            val db = guestDataBase.writableDatabase
            val values = ContentValues().apply {
                val presence = if (guest.presence) 1 else 0
                put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
                put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)
            }
            val selection = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val args = arrayOf(guest.id.toString())
            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)
            true
        } catch (e: Exception){
            e.printStackTrace()
            false
        }
    }
    fun delete(guestID: Int):Boolean{
        return try {
            val db = guestDataBase.writableDatabase

            val selection = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val args = arrayOf(guestID.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception){
            e.printStackTrace()
            false
        }
    }
    fun getAll(){

    }

}