package me.meenagopal24.notexpert.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = LocalNotesDatabase.Schema,
            name = "LocalNotesDatabase.db"
        )
    }
}