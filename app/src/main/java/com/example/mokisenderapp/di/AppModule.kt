package com.example.mokisenderapp.di

import android.content.Context
import com.example.mokisenderapp.R
import com.example.mokisenderapp.data.retrofit.RetrofitService
import com.example.mokisenderapp.db.FactDao
import com.example.mokisenderapp.db.FactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext appContext: Context): RetrofitService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(appContext.getString(R.string.ppal_url))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonDao(pokemonDatabase: FactDatabase): FactDao {
        return pokemonDatabase.factDao
    }

    @Provides
    @Singleton
    fun providePokeDatabase(@ApplicationContext appContext: Context): FactDatabase {
        return FactDatabase.getInstance(appContext)
    }

}