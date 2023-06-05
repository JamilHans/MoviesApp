package com.trainingandroid.mobiedbapp.presentation.di

import com.trainingandroid.data.api.NetworkResponseAdapterFactory
import com.trainingandroid.data.api.RemoteService
import com.trainingandroid.data.datasource.MovieDetailRemoteDataSource
import com.trainingandroid.data.datasource.MovieDetailRemoteDataSourceImp
import com.trainingandroid.data.datasource.MoviesRemoteDataSource
import com.trainingandroid.data.datasource.MoviesRemoteDataSourceImp
import com.trainingandroid.data.repositories.MovieDetailRepositoryImpl
import com.trainingandroid.data.repositories.MoviesRepositoryImpl
import com.trainingandroid.domain.repositories.MovieDetailRepository
import com.trainingandroid.domain.repositories.MoviesRepository
import com.trainingandroid.domain.usecase.GetDetailMovieUseCase
import com.trainingandroid.domain.usecase.GetDetailMovieUseCaseImpl
import com.trainingandroid.domain.usecase.GetPopulateMoviesUseCase
import com.trainingandroid.domain.usecase.GetPopulateMoviesUseCaseImpl
import com.trainingandroid.domain.usecase.GetUpcomingMoviesUseCase
import com.trainingandroid.domain.usecase.GetUpcomingMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideRemoteService(retrofit: Retrofit): RemoteService {
        return retrofit.create(RemoteService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
class AppBindModule {

    @Provides
    fun provideMoviesRemoteDataSource(remoteService: RemoteService): MoviesRemoteDataSource {
        return MoviesRemoteDataSourceImp(remoteService)
    }

    @Provides
    fun provideMovieDetailRemoteDataSource(remoteService: RemoteService): MovieDetailRemoteDataSource {
        return MovieDetailRemoteDataSourceImp(remoteService)
    }
}

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetDetailMovieUseCase(movieDetailRepository: MovieDetailRepository): GetDetailMovieUseCase {
        return GetDetailMovieUseCaseImpl(movieDetailRepository)
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
    fun provideMoviesRepository(moviesRemoteDataSource: MoviesRemoteDataSource): MoviesRepository {
        return MoviesRepositoryImpl(moviesRemoteDataSource)
    }

    @Provides
    fun provideMovieDetailRepository(movieDetailRemoteDataSource: MovieDetailRemoteDataSource): MovieDetailRepository{
        return MovieDetailRepositoryImpl(movieDetailRemoteDataSource)
    }
}
