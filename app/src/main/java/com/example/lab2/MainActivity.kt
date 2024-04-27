package com.example.lab2 // Объявление пакета

import android.content.Intent // Импорт класса Intent для перехода к другому активити
import android.database.sqlite.SQLiteDatabase
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
        val db = dbHelper.writableDatabase // Получаем доступ к базе данных

        // Проверяем существование таблицы
        if (!checkTableExists(DatabaseHelper.TABLE_NAME, db)) {
            // Создаем таблицу, если ее нет
            db.execSQL(DatabaseHelper.SQL_CREATE_ENTRIES)
        }

        // Удаление всех записей из БД
        dbHelper.deleteAllRecords(db)

        // Внесение 5 записей об одногруппниках
        dbHelper.insertInitialRecords(db)
    }

    fun checkTableExists(tableName: String, db: SQLiteDatabase): Boolean {
        val cursor = db.rawQuery("SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = '$tableName'", null)
        val tableExists = cursor.moveToFirst()
        cursor.close()
        return tableExists
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
        dbHelper.insertRecord("Шмелев Геннадий Вадимович")
        // Отображение сообщения об успешном добавлении
        Toast.makeText(this, "Добавлена новая запись", Toast.LENGTH_SHORT).show()
    }

    // Метод для обновления последней записи
    fun updateRecord(view: View) {
        // Обновление последней записи в базе данных
        dbHelper.updateLastRecord("Иванов Иван Иванович")
        // Отображение сообщения об успешном обновлении
        Toast.makeText(this, "Обновлена последняя запись", Toast.LENGTH_SHORT).show()
    }
}
