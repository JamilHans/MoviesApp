package com.trainingandroid.mobiedbapp.presentation.di

import com.trainingandroid.domain.repositories.MoviesRepository
import com.trainingandroid.domain.usecase.GetDetailMovieUseCaseImpl
import com.trainingandroid.domain.usecase.GetPopulateMoviesUseCaseImpl
import com.trainingandroid.domain.usecase.GetUpcomingMoviesUseCaseImpl
import com.trainingandroid.data.api.RemoteService
import com.trainingandroid.data.datasource.MoviesRemoteDataSource
import com.trainingandroid.data.repositories.MoviesRepositoryImpl
import com.trainingandroid.domain.usecase.GetDetailMovieUseCase
import com.trainingandroid.domain.usecase.GetPopulateMoviesUseCase
import com.trainingandroid.domain.usecase.GetUpcomingMoviesUseCase
import com.trainingandroid.data.datasource.MoviesRemoteDataSourceImp
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
    fun provideRemoteService(): RemoteService {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()
    }
}

@Module
@InstallIn(SingletonComponent::class)
class AppBindModule {

    @Provides
    fun provideMoviesRemoteDataSource(remoteService: RemoteService): MoviesRemoteDataSource {
        return MoviesRemoteDataSourceImp(remoteService)
    }
}

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetDetailMovieUseCase(recipeRepository: MoviesRepository): GetDetailMovieUseCase {
        return GetDetailMovieUseCaseImpl(recipeRepository)
    }

    @Provides
    fun provideGetPopulateMoviesUseCase(recipeRepository: MoviesRepository): GetPopulateMoviesUseCase {
        return GetPopulateMoviesUseCaseImpl(recipeRepository)
    }

    @Provides
    fun provideGetUpcomingMoviesUseCase(recipeRepository: MoviesRepository): GetUpcomingMoviesUseCase {
        return GetUpcomingMoviesUseCaseImpl(recipeRepository)
    }

}

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideMoviesRepository(recipeRepository: MoviesRemoteDataSource): MoviesRepository {
        return MoviesRepositoryImpl(recipeRepository)
    }
}