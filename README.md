**Цель работы**

Изучить работу Android приложения с базой данных.

**Задание**

Необходимо создать приложение, взаимодействующее с базой данных. 
•	Первое активити должно содержать три кнопки. 
•	При нажатии на первую кнопку должно открываться новое активити, выво-дящее информацию из таблицы «Одногруппники» в удобном для восприя-тия формате.
•	При запуске приложения необходимо:
1.    Создать БД, если ее не существует.
2.    Создать таблицу «Одногруппники», содержащую поля:
·	ID;
·	ФИО;
·	Время добавления записи.
3.    Удалять все записи из БД, а затем вносить 5 записей об одногруппни-ках.
•	При нажатии на вторую кнопку необходимо внести еще одну запись в таб-лицу.
•	При нажатии на третью кнопку необходимо заменить ФИО в последней внесенной записи на Иванов Иван Иванович.

**Ход выполнения и результат работы**
  
Создаем проект Android приложения в Android Studio: начинаем, создавая но-вый проект в Android Studio с использованием шаблона "Empty Activity".
Определяем структуру базы данных: определяем структуру базы данных, зада-вая таблицу как "Одногруппники (Students.db)" с полями: ID, ФИО и время добавления записи (Рисунок 1).

![Рисунок1](https://github.com/nuafirytiasewo/lab2/assets/103138302/9fd5829d-a8c8-461d-8d47-30328b52e4b8)
Рисунок 1 – Структура базы данных в коде

Создаем класс для работы с базой данных (DatabaseHelper): разрабатываем класс DatabaseHelper, который отвечает за взаимодействие с базой данных. В этом классе определяем методы для создания базы данных, таблицы "Одногруппники", удаления всех записей из таблицы и добавления начальных записей (Рисунок 2).

![Рисунок2](https://github.com/nuafirytiasewo/lab2/assets/103138302/f901ac8a-b080-4356-80d4-a45171030a18)
Рисунок 2 – Класс DatabaseHelper

Разрабатываем главную активность (MainActivity): создаем класс MainActivity, который будет главной активностью приложения. В этом классе создаем пользовательский интерфейс с кнопками для управления базой данных.
Реализуем логику кнопок: в методе onCreate активности MainActivity подключа-ем базу данных и устанавливаем обработчики для кнопок. При нажатии на кнопки вызы-ваем соответствующие методы работы с базой данных.
Отображаем список записей о одногруппниках: создаем макет activity_records.xml для отображения списка записей о одногруппниках. Используем RecyclerView и адаптер для отображения списка записей, а также создаем класс RecordsAdapter для связи данных с RecyclerView.
Тестируем приложение: запускаем приложение на устройстве или эмуляторе Android и проверяем его функциональность. Проводим тестирование работы кнопок, корректности отображения списка записей и взаимодействия с базой данных. 
При запуске приложения, мы видим меню (Рисунок 3)

![Рисунок3](https://github.com/nuafirytiasewo/lab2/assets/103138302/d45fdb96-98d4-4c55-92a9-a24b272a0060)
Рисунок 3 – Активность MainActivity

При нажатии на кнопку «Показать записи», мы видим, что были созданы 5 запи-сей, еще при запуске приложения (Рисунок 4).

![Рисунок4](https://github.com/nuafirytiasewo/lab2/assets/103138302/e7ee8477-2e92-4470-84b5-4c899c75d753)
Рисунок 4 – 5 записей были созданы после запуска в активности просмотра записей

При нажатии на кнопку «Добавить запись», всплывает уведомление, и, при заходе в просмотр записей, мы видим, что создалась новая запись (Рисунок 5)
 
![Рисунок6](https://github.com/nuafirytiasewo/lab2/assets/103138302/ee91afbf-21ff-4a20-b371-7679ceb466fe)
![Рисунок5](https://github.com/nuafirytiasewo/lab2/assets/103138302/745d8573-25b0-41aa-9be7-3f551fcb1ff9)
Рисунок 5 – Добавлена новая запись

При нажатии на кнопку «Обновить последнюю запись», всплывает уведомление, и, при заходе в просмотр записей, мы видим, что обновлена последняя запись (Рисунок 6)
 
![Рисунок8](https://github.com/nuafirytiasewo/lab2/assets/103138302/e3d3a452-7b09-46ac-b307-34b637c111d1)
![Рисунок7](https://github.com/nuafirytiasewo/lab2/assets/103138302/0e67ec62-d696-4131-9aed-a9dec2df685b)
Рисунок 6 – Обновлена последняя запись
