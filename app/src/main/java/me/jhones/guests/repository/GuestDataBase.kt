package me.jhones.guests.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import me.jhones.guests.constants.DataBaseConstants

class GuestDataBase(context: Context) : SQLiteOpenHelper(context, DATA_BASE_NAME, null, VERSION) {

    companion object {
        private const val DATA_BASE_NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val tableName = DataBaseConstants.GUEST.TABLE_NAME
        val id = DataBaseConstants.GUEST.COLUMNS.ID
        val name = DataBaseConstants.GUEST.COLUMNS.NAME
        val presence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

        db.execSQL("create table $tableName (" +
                "$id Integer primary key autoIncrement, " +
                "$name Text, " +
                "$presence Integer)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

}