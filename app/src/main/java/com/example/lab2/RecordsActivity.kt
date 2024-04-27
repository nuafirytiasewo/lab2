package com.example.lab2 // Объявление пакета

import android.os.Bundle // Импорт класса Bundle для работы с данными активити
import androidx.appcompat.app.AppCompatActivity // Импорт класса AppCompatActivity для создания активити
import androidx.recyclerview.widget.LinearLayoutManager // Импорт класса LinearLayoutManager для настройки макета списка
import androidx.recyclerview.widget.RecyclerView // Импорт класса RecyclerView для отображения списка записей

class RecordsActivity : AppCompatActivity() { // Определение класса RecordsActivity, наследующего AppCompatActivity

    private lateinit var recyclerView: RecyclerView // Объявление переменной для RecyclerView
    private lateinit var adapter: RecordsAdapter // Объявление переменной для адаптера списка
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) { // Переопределение метода onCreate для инициализации активити
        super.onCreate(savedInstanceState) // Вызов родительского метода onCreate
        setContentView(R.layout.activity_records) // Установка макета для активити

        dbHelper = DatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerView) // Поиск RecyclerView по идентификатору
        recyclerView.layoutManager = LinearLayoutManager(this) // Установка LayoutManager для RecyclerView
        adapter = RecordsAdapter(getRecordsFromDatabase()) // Инициализация адаптера с данными из базы данных
        recyclerView.adapter = adapter // Установка адаптера для RecyclerView
    }

    private fun getRecordsFromDatabase(): List<Record> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_NAME, null, null, null, null, null,
            "${DatabaseHelper.COLUMN_ID} ASC"
        )
        val records = mutableListOf<Record>()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME))
            val date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TIMESTAMP))
            records.add(Record(id, name, date))
        }

        cursor.close()
        db.close()

        return records
    }
}
