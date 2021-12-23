package br.com.vostre.visid.di

import android.app.Application
import br.com.vostre.visid.data.AppDatabase
import br.com.vostre.visid.data.dao.CorDao
import br.com.vostre.visid.data.dao.ProjetoDao
import br.com.vostre.visid.data.repository.CorRepositoryImpl
import br.com.vostre.visid.data.repository.ProjetoRepositoryImpl
import br.com.vostre.visid.domain.repository.CorRepository
import br.com.vostre.visid.domain.repository.ProjetoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : AppDatabase {
        return AppDatabase.getInstance(app)
    }

    @Provides
    @Singleton
    fun provideProjetoDao(app: Application) : ProjetoDao {
        return AppDatabase.getInstance(app).getProjetoDao()
    }

    @Provides
    @Singleton
    fun provideCorDao(app: Application) : CorDao {
        return AppDatabase.getInstance(app).getCorDao()
    }

    @Provides
    @Singleton
    fun provideProjetoRepository(db: AppDatabase): ProjetoRepository {
        return ProjetoRepositoryImpl(db)
    }

    @Provides
    @Singleton
    fun provideCorRepository(db: AppDatabase): CorRepository {
        return CorRepositoryImpl(db)
    }

}