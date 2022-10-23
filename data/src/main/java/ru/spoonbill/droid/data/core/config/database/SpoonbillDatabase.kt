package ru.spoonbill.droid.data.core.config.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.spoonbill.droid.data.core.config.database.SpoonbillDatabase.Companion.DB_VERSION
import ru.spoonbill.droid.data.core.features.cart.local.dao.CartDao
import ru.spoonbill.droid.data.core.features.product.local.dao.ProductDao
import ru.spoonbill.droid.data.core.features.cart.local.enitity.CartEntity
import ru.spoonbill.droid.data.core.features.category.local.dao.CategoryDao
import ru.spoonbill.droid.data.core.features.category.local.entity.CategoryEntity
import ru.spoonbill.droid.data.core.features.product.local.entity.ProductEntity

@Database(entities = [CartEntity::class, ProductEntity::class, CategoryEntity::class], version = DB_VERSION)
abstract class SpoonbillDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        internal const val DB_VERSION = 5
        private const val DB_NAME = "ru.spoonbill.app.DATABASE"

        private var INSTANCE: SpoonbillDatabase? = null

        @Synchronized
        fun newInstance(context: Context): SpoonbillDatabase {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    SpoonbillDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE ?: throw NullPointerException("The ${SpoonbillDatabase::class.simpleName} instance must not be null!")
        }
    }
}