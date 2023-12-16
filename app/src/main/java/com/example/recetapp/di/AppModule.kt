package com.example.recetapp.di

import com.example.recetapp.data.remote.RecipesRemoteDataSource
import com.example.recetapp.data.remote.RecipesService
import com.example.recetapp.data.repository.RecipesRepository
import com.example.recetapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLogginInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(logging).connectTimeout(60, TimeUnit.SECONDS).readTimeout(60,
            TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipesService(retrofit: Retrofit): RecipesService{
        return retrofit.create(RecipesService::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipesRemoteDataSource(recipesService: RecipesService): RecipesRemoteDataSource {
        return RecipesRemoteDataSource(recipesService)
    }

    @Provides
    @Singleton
    fun provideRecipesRepository(recipesRemoteDataSource: RecipesRemoteDataSource): RecipesRepository{
        return RecipesRepository(recipesRemoteDataSource)
    }

}