package com.example.lab4

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

data class User(
    val name: String,
    val email: String,
    val contact: String,
    val address: String,
    val password: String
)

data class Task(
    val id: Int,
    val title: String,
    val type: String,
    val module: String,
    val date: String,
    val description: String
)

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERS = "users"
        private const val TABLE_TASKS = "tasks"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createUsersTable = ("CREATE TABLE $TABLE_USERS (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "email TEXT," +
                "contact TEXT," +
                "address TEXT," +
                "password TEXT)")

        val createTasksTable = ("CREATE TABLE $TABLE_TASKS (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "type TEXT," +
                "module TEXT," +
                "date TEXT," +
                "description TEXT)")

        db.execSQL(createUsersTable)
        db.execSQL(createTasksTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TASKS")
        onCreate(db)
    }

    fun addUser(name: String, email: String, contact: String, address: String, password: String): Long {
        val db = this.writableDatabase
        var newRowId: Long = -1
        try {
            val values = ContentValues().apply {
                put("name", name)
                put("email", email)
                put("contact", contact)
                put("address", address)
                put("password", password)
            }
            newRowId = db.insert(TABLE_USERS, null, values)
        } catch (e: Exception) {
            Log.e("DBHelper", "Error adding user", e)
        } finally {
            db.close()
        }
        return newRowId
    }

    fun addTask(title: String, type: String, module: String, date: String, description: String) {
        val db = this.writableDatabase
        try {
            val values = ContentValues().apply {
                put("title", title)
                put("type", type)
                put("module", module)
                put("date", date)
                put("description", description)
            }
            db.insert(TABLE_TASKS, null, values)
        } catch (e: Exception) {
            Log.e("DBHelper", "Error adding task", e)
        } finally {
            db.close()
        }
    }

    fun getAllTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        val db = this.readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM $TABLE_TASKS", null)

        cursor?.use {
            val idIndex = it.getColumnIndex("id")
            val titleIndex = it.getColumnIndex("title")
            val typeIndex = it.getColumnIndex("type")
            val moduleIndex = it.getColumnIndex("module")
            val dateIndex = it.getColumnIndex("date")
            val descriptionIndex = it.getColumnIndex("description")

            while (it.moveToNext()) {
                tasks.add(Task(
                    id = it.getInt(idIndex),
                    title = it.getString(titleIndex),
                    type = it.getString(typeIndex),
                    module = it.getString(moduleIndex),
                    date = it.getString(dateIndex),
                    description = it.getString(descriptionIndex)
                ))
            }
        }
        db.close()
        return tasks
    }

    @SuppressLint("Range")
    fun getUserByEmail(email: String): User? {
        val db = this.readableDatabase
        val cursor: Cursor? = db.query(
            TABLE_USERS,
            arrayOf("id", "name", "email", "contact", "address", "password"),
            "email=?",
            arrayOf(email),
            null,
            null,
            null
        )

        var user: User? = null
        cursor?.use {
            if (it.moveToFirst()) {
                val nameIndex = it.getColumnIndex("name")
                val emailIndex = it.getColumnIndex("email")
                val contactIndex = it.getColumnIndex("contact")
                val addressIndex = it.getColumnIndex("address")
                val passwordIndex = it.getColumnIndex("password")

                Log.d("DBHelper", "User found: ${it.count} entries")

                user = User(
                    it.getString(nameIndex),
                    it.getString(emailIndex),
                    it.getString(contactIndex),
                    it.getString(addressIndex),
                    it.getString(passwordIndex)
                )
            } else {
                Log.d("DBHelper", "No user found with email $email")
            }
        }
        db.close()
        return user
    }

}