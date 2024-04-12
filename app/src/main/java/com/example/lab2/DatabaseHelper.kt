package com.example.lab2 // Объявление пакета

import android.content.ContentValues // Импорт класса ContentValues для работы с данными
import android.content.Context // Импорт класса Context для доступа к ресурсам приложения
import android.database.sqlite.SQLiteDatabase // Импорт класса SQLiteDatabase для работы с базой данных SQLite
import android.database.sqlite.SQLiteOpenHelper // Импорт класса SQLiteOpenHelper для управления базой данных SQLite
import android.os.AsyncTask

// Класс для работы с базой данных SQLite
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Метод вызывается при создании базы данных
    override fun onCreate(db: SQLiteDatabase) {
        // Создание таблицы в базе данных при ее создании
        db.execSQL(SQL_CREATE_ENTRIES)
        // Добавление начальных записей после создания базы данных
        insertInitialRecords(db)
    }

    // Метод вызывается при обновлении базы данных
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Удаление старой версии таблицы и создание новой версии при обновлении базы данных
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    // Метод для добавления новой записи в таблицу
    fun insertRecord(name: String) {
        // Запуск асинхронной задачи для добавления записи
        InsertRecordTask(this).execute(name)
    }

    // Метод для обновления последней записи в таблице
    fun updateLastRecord(newName: String) {
        // Запуск асинхронной задачи для обновления последней записи
        UpdateLastRecordTask(this, newName).execute()
    }

    // Метод для удаления всех записей из таблицы
    fun deleteAllRecords() {
        // Получение экземпляра базы данных для записи
        val db = writableDatabase
        // Удаление всех записей из таблицы
        db.delete(TABLE_NAME, null, null)
    }

    // Метод для добавления начальных записей в таблицу
    fun insertInitialRecords() {
        // Удаление всех записей перед добавлением новых записей
        deleteAllRecords()
        // Добавление начальных записей
        repeat(5) {
            // Формирование имени студента
            val name = "Студент ${it + 1}"
            // Добавление записи в таблицу
            insertRecord(name)
        }
    }

    // Внутренний класс для асинхронной задачи добавления записи
    private class InsertRecordTask(private val dbHelper: DatabaseHelper) : AsyncTask<String, Void, Void>() {
        override fun doInBackground(vararg params: String?): Void? {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_NAME, params[0])
                put(COLUMN_TIMESTAMP, System.currentTimeMillis())
            }
            db.insert(TABLE_NAME, null, values)
            return null
        }
    }

    // Внутренний класс для асинхронной задачи обновления последней записи
    private class UpdateLastRecordTask(private val dbHelper: DatabaseHelper, private val newName: String) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_NAME, newName)
            }
            val whereClause = "$COLUMN_ID = (SELECT MAX($COLUMN_ID) FROM $TABLE_NAME)"
            db.update(TABLE_NAME, values, whereClause, null)
            return null
        }
    }

    // Статические поля класса для описания базы данных и таблицы
    companion object {
        const val DATABASE_VERSION = 1 // Версия базы данных
        const val DATABASE_NAME = "Students.db" // Имя базы данных
        const val TABLE_NAME = "students" // Имя таблицы
        const val COLUMN_ID = "_id" // Название столбца с ID
        const val COLUMN_NAME = "name" // Название столбца с именем
        const val COLUMN_TIMESTAMP = "timestamp" // Название столбца с временной меткой

        // SQL-запрос для создания таблицы
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_NAME TEXT," +
                    "$COLUMN_TIMESTAMP INTEGER)"

        // SQL-запрос для удаления таблицы
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}