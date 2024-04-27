package com.example.lab2 // Объявление пакета

import android.view.LayoutInflater // Импорт класса LayoutInflater для создания макета из XML
import android.view.View // Импорт класса View для работы с элементами пользовательского интерфейса
import android.view.ViewGroup // Импорт класса ViewGroup для работы с контейнерами пользовательского интерфейса
import android.widget.TextView // Импорт класса TextView для отображения текстовых данных
import androidx.recyclerview.widget.RecyclerView // Импорт класса RecyclerView для отображения списка записей

data class Record(val id: Int, val name: String, val date: String) // Определение класса данных Record

class RecordsAdapter(private val records: List<Record>) : RecyclerView.Adapter<RecordsAdapter.RecordViewHolder>() { // Определение класса адаптера для RecyclerView

    class RecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { // Определение класса ViewHolder для элемента списка
        val idTextView: TextView = itemView.findViewById(R.id.idTextView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView) // Инициализация TextView для имени записи
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView) // Инициализация TextView для временной метки записи
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder { // Переопределение метода onCreateViewHolder
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_record, parent, false) // Создание макета элемента списка
        return RecordViewHolder(itemView) // Возврат созданного ViewHolder
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) { // Переопределение метода onBindViewHolder
        val record = records[position] // Получение записи по позиции
        holder.idTextView.text = record.id.toString()
        holder.nameTextView.text = record.name // Установка имени записи в TextView
        holder.dateTextView.text = record.date // Установка временной метки записи в TextView
    }

    override fun getItemCount(): Int { // Переопределение метода getItemCount
        return records.size // Возврат количества записей
    }
}
