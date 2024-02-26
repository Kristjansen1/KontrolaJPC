package com.example.kontrolajpc.di

import android.content.Context
import androidx.room.Room
import com.example.kontrolajpc.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


/*    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Application): KontrolaJPC {
        return app as KontrolaJPC
    }
    */
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "database"
    ).build()


    @Singleton
    @Provides
    fun providesDao(db: AppDatabase) = db.faultDao()

}