package com.example.lab2 // Объявление пакета

import android.view.LayoutInflater // Импорт класса LayoutInflater для создания макета из XML
import android.view.View // Импорт класса View для работы с элементами пользовательского интерфейса
import android.view.ViewGroup // Импорт класса ViewGroup для работы с контейнерами пользовательского интерфейса
import android.widget.TextView // Импорт класса TextView для отображения текстовых данных
import androidx.recyclerview.widget.RecyclerView // Импорт класса RecyclerView для отображения списка записей

data class Record(val id: Long, val name: String, val timestamp: String) // Определение класса данных Record

class RecordsAdapter(private val records: List<Record>) : RecyclerView.Adapter<RecordsAdapter.ViewHolder>() { // Определение класса адаптера для RecyclerView

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { // Определение класса ViewHolder для элемента списка
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView) // Инициализация TextView для имени записи
        val timestampTextView: TextView = itemView.findViewById(R.id.timestampTextView) // Инициализация TextView для временной метки записи
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { // Переопределение метода onCreateViewHolder
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_record, parent, false) // Создание макета элемента списка
        return ViewHolder(itemView) // Возврат созданного ViewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { // Переопределение метода onBindViewHolder
        val record = records[position] // Получение записи по позиции
        holder.nameTextView.text = record.name // Установка имени записи в TextView
        holder.timestampTextView.text = record.timestamp // Установка временной метки записи в TextView
    }

    override fun getItemCount(): Int { // Переопределение метода getItemCount
        return records.size // Возврат количества записей
    }
}
