package com.example.lab2 // Объявление пакета

import android.os.Bundle // Импорт класса Bundle для работы с данными активити
import androidx.appcompat.app.AppCompatActivity // Импорт класса AppCompatActivity для создания активити
import androidx.recyclerview.widget.LinearLayoutManager // Импорт класса LinearLayoutManager для настройки макета списка
import androidx.recyclerview.widget.RecyclerView // Импорт класса RecyclerView для отображения списка записей

class RecordsActivity : AppCompatActivity() { // Определение класса RecordsActivity, наследующего AppCompatActivity

    private lateinit var recyclerView: RecyclerView // Объявление переменной для RecyclerView
    private lateinit var adapter: RecordsAdapter // Объявление переменной для адаптера списка

    override fun onCreate(savedInstanceState: Bundle?) { // Переопределение метода onCreate для инициализации активити
        super.onCreate(savedInstanceState) // Вызов родительского метода onCreate
        setContentView(R.layout.activity_records) // Установка макета для активити

        recyclerView = findViewById(R.id.recyclerView) // Поиск RecyclerView по идентификатору
        recyclerView.layoutManager = LinearLayoutManager(this) // Установка LayoutManager для RecyclerView
        adapter = RecordsAdapter(getRecordsFromDatabase()) // Инициализация адаптера с данными из базы данных
        recyclerView.adapter = adapter // Установка адаптера для RecyclerView
    }

    private fun getRecordsFromDatabase(): List<Record> {
        // В данном примере возвращается фиктивный список записей
        return listOf(
            Record(1, "Иванов Иван Иванович", "01.04.2024"),
            Record(2, "Петров Петр Петрович", "02.04.2024"),
            Record(3, "Сидоров Сидор Сидорович", "03.04.2024"),
            Record(4, "Алексеев Алексей Алексеевич", "04.04.2024"),
            Record(5, "Николаев Николай Николаевич", "05.04.2024")
        )
    }
}
