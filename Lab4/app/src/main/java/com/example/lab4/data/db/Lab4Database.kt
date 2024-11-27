package com.example.lab4.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lab4.data.dao.SubjectDao
import com.example.lab4.data.dao.SubjectLabsDao
import com.example.lab4.data.entity.SubjectEntity
import com.example.lab4.data.entity.SubjectLabEntity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Lab4Database - the main database class
 * - extends on RoomDatabase()
 * - marked with @Database annotation for generating communication interfaces
 * - in annotation are added all your entities (tables)
 * - includes abstract properties of all DAO interfaces for each entity (table)
 */
@Database(entities = [SubjectEntity::class, SubjectLabEntity::class], version = 1)
abstract class Lab4Database : RoomDatabase() {
    //DAO properties for each entity (table)
    // must be abstract (because Room will generate instances by itself)
    abstract val subjectsDao: SubjectDao
    abstract val subjectLabsDao: SubjectLabsDao
}

/**
 * DatabaseStorage - custom class where you initialize and store Lab4Database single instance
 *
 */
object DatabaseStorage {
    // ! Important - all operations with DB must be done from non-UI thread!
    // coroutineScope: CoroutineScope - is the scope which allows to run asynchronous operations
    // > we will learn it soon! For now just put it here
    private val coroutineScope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        },
    )

    // single instance of Lab4Database
    private var _database: Lab4Database? = null

    /**
        Function of initializing and getting Lab4Database instance
        - is invoked from place where DB should be used (from Compose screens)
        [context] - context from Compose screen to init DB
    */
    fun getDatabase(context: Context): Lab4Database {
        // if _database already contains Lab4Database instance, return this instance
        if (_database != null) return _database as Lab4Database
        // if not, create instance, preload some data and return this instance
        else {
            // creating Lab4Database instance by builder
            _database = Room.databaseBuilder(
                context,
                Lab4Database::class.java, "lab4Database"
            ).build()

            // preloading some data to DB
            preloadData()

            return _database as Lab4Database
        }
    }

    /**
        Function for preloading some initial data to DB
     */
    private fun preloadData() {
        // List of subjects
        val listOfSubject = listOf(
            SubjectEntity(id = 1, title = "Глобальні інформаційні мережі"),
            SubjectEntity(id = 2, title = "Мережні операційні системи"),
            SubjectEntity(id = 3, title = "Автоматизоване проєктування комп'ютерних систем"),
            SubjectEntity(id = 4, title = "Програмування мобільних додатків")

        )
        // List of labs
        val listOfSubjectLabs = listOf(
            SubjectLabEntity(
                id = 1,
                subjectId = 1,
                title = "Основи TCP/IP",
                description = "Вивчення протоколів мережного рівня та побудови IP-мереж.",
                comment = "Корисно для загального розуміння мереж.",
                isCompleted = true
            ),
            SubjectLabEntity(
                id = 2,
                subjectId = 1,
                title = "Налаштування маршрутизаторів",
                description = "Практична робота з налаштування маршрутизаторів для локальних мереж.",
                comment = "Непроста, але цікава лабораторна.",
                inProgress = true
            ),
            SubjectLabEntity(
                id = 3,
                subjectId = 1,
                title = "Аналіз мережного трафіку",
                description = "Розбір інструментів для аналізу трафіку, таких як Wireshark.",
                comment = "Дуже корисно для мережних спеціалістів."
            ),

            // Мережні операційні системи
            SubjectLabEntity(
                id = 4,
                subjectId = 2,
                title = "Встановлення Linux-сервера",
                description = "Інсталяція та налаштування серверної ОС Linux.",
                comment = "Перша лабораторна робота з цієї теми.",
                isCompleted = true
            ),
            SubjectLabEntity(
                id = 5,
                subjectId = 2,
                title = "Управління користувачами і правами доступу",
                description = "Робота з налаштування прав доступу в Linux.",
                comment = "Корисно для адміністрування.",
                inProgress = true
            ),
            SubjectLabEntity(
                id = 6,
                subjectId = 2,
                title = "Налаштування брандмауера",
                description = "Робота з конфігуруванням брандмауера iptables.",
                comment = "Складно, але дає гарні знання з безпеки."
            ),

            // Автоматизоване проєктування комп'ютерних систем
            SubjectLabEntity(
                id = 7,
                subjectId = 3,
                title = "Моделювання архітектури комп'ютера",
                description = "Побудова і моделювання архітектури простої комп'ютерної системи.",
                comment = "Цікаво для тих, хто хоче розуміти апаратну частину.",
                isCompleted = true
            ),
            SubjectLabEntity(
                id = 8,
                subjectId = 3,
                title = "Синтез логічних схем",
                description = "Створення логічних схем для процесорів та контролерів.",
                comment = "Трохи складно, але цікаво!",
                inProgress = true
            ),
            SubjectLabEntity(
                id = 9,
                subjectId = 3,
                title = "Автоматизований тест системи",
                description = "Розробка тестів для перевірки роботи створених схем.",
                comment = "Важливо для розуміння процесу тестування."
            ),

            // Програмування мобільних додатків
            SubjectLabEntity(
                id = 10,
                subjectId = 4,
                title = "Основи Android SDK",
                description = "Вивчення основ роботи з Android SDK та створення першого додатку.",
                comment = "Вступ до мобільного програмування.",
                isCompleted = true
            ),
            SubjectLabEntity(
                id = 11,
                subjectId = 4,
                title = "Робота з базами даних у мобільних додатках",
                description = "Налаштування локальної бази даних SQLite в Android додатку.",
                comment = "Корисно для роботи з даними.",
                inProgress = true
            ),
            SubjectLabEntity(
                id = 12,
                subjectId = 4,
                title = "Використання API для мобільних додатків",
                description = "Інтеграція REST API в мобільний додаток для отримання даних з інтернету.",
                comment = "Цікаво, коли знаєш, як працюють API."
            )
        )

        // Request to add all Subjects from the list to DB
        listOfSubject.forEach { subject ->
            // coroutineScope.launch{...} - start small thread where you can make query to DB
            coroutineScope.launch {
                // INSERT query to add Subject (subjectsDao is used)
                _database?.subjectsDao?.addSubject(subject)
            }
        }
        // Request to add all Labs from the list to DB
        listOfSubjectLabs.forEach { lab ->
            coroutineScope.launch {
                // INSERT query to add Lab (subjectLabsDao is used)
                _database?.subjectLabsDao?.addSubjectLab(lab)
            }
        }
    }
}