package me.meenagopal24.notexpert.db

import android.content.Context
import androidx.startup.Initializer
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver


object NotesApp {
    lateinit var applicationContext: Context
    fun init(context: Context) {
        applicationContext = context
    }
}

class AppInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        NotesApp.init(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> = listOf()
}

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = LocalNotesDatabase.Schema,
            context = NotesApp.applicationContext,
            name = "LocalNotesDatabase.db"
        )
    }
}