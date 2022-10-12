package me.jhones.guests.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
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
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
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
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun deleteGuest(id: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val selection = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    private fun createCursor(filter: Filter, db: SQLiteDatabase): Cursor {
        val selection = "${DataBaseConstants.GUEST.COLUMNS.PRESENCE} = ?"
        val selectionArgs = when (filter) {
            Filter.PRESENT -> arrayOf("1")
            Filter.ABSENT ->  arrayOf("0")
            Filter.ALL -> null
        }
        val selectedColumns = arrayOf(
            DataBaseConstants.GUEST.COLUMNS.ID,
            DataBaseConstants.GUEST.COLUMNS.NAME,
            DataBaseConstants.GUEST.COLUMNS.PRESENCE
        )

       val cursor: Cursor = when(filter){
            Filter.ALL -> {
                db.query(
                    DataBaseConstants.GUEST.TABLE_NAME,
                    selectedColumns,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            }
            Filter.PRESENT -> {
                db.query(
                    DataBaseConstants.GUEST.TABLE_NAME,
                    selectedColumns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
                )
            }
            Filter.ABSENT -> { db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                selectedColumns,
                selection,
                selectionArgs,
                null,
                null,
                null
            )}
        }

        return cursor

    }

    fun getGuests(filter: Filter): List<GuestModel> {
        val guests = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val cursor = createCursor(filter,db)

            if ( cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val columnIndexID = cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID)
                    val columnIndexName =
                        cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME)
                    val columnIndexPresence =
                        cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)

                    val id = cursor.getInt(columnIndexID)
                    val name = cursor.getString(columnIndexName)
                    val presence: Boolean = (cursor.getInt(columnIndexPresence) == 1)

                    val currentGuest = GuestModel(id, name, presence)
                    guests.add(currentGuest)
                }
            }

            cursor.close()
            return guests

        } catch (e: Exception) {
            e.printStackTrace()
            return guests
        }

    }

}