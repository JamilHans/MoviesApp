package com.trainingandroid.mobiedbapp.di

import com.trainingandroid.mobiedbapp.data.api.RemoteService
import com.trainingandroid.mobiedbapp.data.datasource.MoviesRemoteDataSource
import com.trainingandroid.mobiedbapp.framework.MoviesRemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideRemoteService(): RemoteService{
        return  Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindModule{

    @Binds
    abstract fun bindMoviesRemoteDataSource(moviesRemoteDataSourceImp: MoviesRemoteDataSourceImp): MoviesRemoteDataSource
}