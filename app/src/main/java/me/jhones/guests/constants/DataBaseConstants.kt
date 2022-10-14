package me.jhones.guests.constants

class DataBaseConstants private constructor() {
    object GUEST {
        const val ID_KEY = "guestId"
        const val TABLE_NAME = "Guest"
        object COLUMNS{
            const val ID= "id"
            const val NAME= "name"
            const val PRESENCE= "presence"
        }
    }
}