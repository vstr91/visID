package br.com.vostre.visid.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.vostre.visid.data.dao.CorDao
import br.com.vostre.visid.data.dao.ProjetoDao
import br.com.vostre.visid.domain.model.Cor
import br.com.vostre.visid.domain.model.Projeto

@Database(
    entities = [
        Projeto::class,
        Cor::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    /* daos */
    abstract fun getProjetoDao() : ProjetoDao
    abstract fun getCorDao() : CorDao

    companion object{
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also{ instance = it}
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {

            return Room.databaseBuilder(context, AppDatabase::class.java, "visid")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                }).build()
        }

    }

}