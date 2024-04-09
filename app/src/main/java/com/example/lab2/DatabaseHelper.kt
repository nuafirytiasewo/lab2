package com.example.lab2 // Объявление пакета

import android.content.ContentValues // Импорт класса ContentValues для работы с данными
import android.content.Context // Импорт класса Context для доступа к ресурсам приложения
import android.database.sqlite.SQLiteDatabase // Импорт класса SQLiteDatabase для работы с базой данных SQLite
import android.database.sqlite.SQLiteOpenHelper // Импорт класса SQLiteOpenHelper для управления базой данных SQLite

// Класс для работы с базой данных
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

    // Метод для удаления всех записей из таблицы
    fun deleteAllRecords() {
        // Получение экземпляра базы данных для записи
        val db = writableDatabase
        // Удаление всех записей из таблицы
        db.delete(TABLE_NAME, null, null)
    }

    // Метод для добавления записи в таблицу
    fun insertRecord(name: String) {
        // Получение экземпляра базы данных для записи
        val db = writableDatabase
        // Создание объекта ContentValues для удобного добавления значений в таблицу
        val values = ContentValues().apply {
            // Установка значений полей записи
            put(COLUMN_NAME, name)
            put(COLUMN_TIMESTAMP, System.currentTimeMillis())
        }
        // Добавление записи в таблицу
        db.insert(TABLE_NAME, null, values)
    }

    // Метод для обновления ФИО последней добавленной записи в таблице
    fun updateLastRecord() {
        // Получение экземпляра базы данных для записи
        val db = writableDatabase
        // Новое ФИО для обновления
        val newName = "Иванов Иван Иванович"
        // Создание объекта ContentValues с новым ФИО
        val values = ContentValues().apply {
            put(COLUMN_NAME, newName)
        }
        // Условие выбора последней записи по ID для обновления
        val whereClause = "$COLUMN_ID = (SELECT MAX($COLUMN_ID) FROM $TABLE_NAME)"
        // Обновление записи в таблице
        db.update(TABLE_NAME, values, whereClause, null)
    }

    // Метод для добавления начальных записей в таблицу
    private fun insertInitialRecords(db: SQLiteDatabase) {
        // Удаление всех записей перед добавлением новых записей
        deleteAllRecords()
        // Добавление 5 начальных записей
        repeat(5) {
            // Формирование имени студента
            val name = "Студент $it"
            // Добавление записи в таблицу
            insertRecord(db, name)
        }
    }

    // Метод для добавления записи в таблицу с указанным экземпляром базы данных
    private fun insertRecord(db: SQLiteDatabase, name: String) {
        // Создание объекта ContentValues для удобного добавления значений в таблицу
        val values = ContentValues().apply {
            // Установка значений полей записи
            put(COLUMN_NAME, name)
            put(COLUMN_TIMESTAMP, System.currentTimeMillis())
        }
        // Добавление записи в таблицу
        db.insert(TABLE_NAME, null, values)
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
                    "$COLUMN_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_NAME TEXT," +
                    "$COLUMN_TIMESTAMP INTEGER)"

        // SQL-запрос для удаления таблицы
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}
