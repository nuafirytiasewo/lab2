package com.example.lab2 // Объявление пакета

import android.content.Intent // Импорт класса Intent для перехода к другому активити
import android.os.Bundle // Импорт класса Bundle для работы с данными активити
import android.view.View // Импорт класса View для работы с пользовательским интерфейсом
import android.widget.Toast // Импорт класса Toast для отображения всплывающих уведомлений
import androidx.appcompat.app.AppCompatActivity // Импорт класса AppCompatActivity для создания активити

class MainActivity : AppCompatActivity() { // Определение класса MainActivity, наследующего AppCompatActivity

    private lateinit var dbHelper: DatabaseHelper // Объявление переменной для работы с базой данных

    override fun onCreate(savedInstanceState: Bundle?) { // Переопределение метода onCreate для инициализации активити
        super.onCreate(savedInstanceState) // Вызов родительского метода onCreate
        setContentView(R.layout.activity_main) // Установка макета для активити

        dbHelper = DatabaseHelper(this) // Инициализация объекта dbHelper для работы с базой данных
    }

    // Метод для отображения записей
    fun showRecords(view: View) {
        // Создание намерения для открытия RecordsActivity
        val intent = Intent(this, RecordsActivity::class.java)
        // Запуск активити
        startActivity(intent)
    }

    // Метод для добавления новой записи
    fun addRecord(view: View) {
        // Добавление новой записи в базу данных
        dbHelper.insertRecord("Новый студент")
        // Отображение сообщения об успешном добавлении
        Toast.makeText(this, "Добавлена новая запись", Toast.LENGTH_SHORT).show()
    }

    // Метод для обновления последней записи
    fun updateRecord(view: View) {
        // Обновление последней записи в базе данных
        dbHelper.updateLastRecord()
        // Отображение сообщения об успешном обновлении
        Toast.makeText(this, "Обновлена последняя запись", Toast.LENGTH_SHORT).show()
    }
}
