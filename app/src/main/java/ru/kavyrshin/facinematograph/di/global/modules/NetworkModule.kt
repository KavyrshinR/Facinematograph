package ru.kavyrshin.facinematograph.di.global.modules

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.kavyrshin.facinematograph.data.network.OmdbApi
import javax.inject.Singleton

@Module
class NetworkModule {
    private val baseUrl : String = "http://www.omdbapi.com/"

    @Provides
    @Singleton
    fun provideGson() : Gson {
        return Gson()
    }

    @Provides
    fun provideOkHttp() : OkHttpClient {
        return OkHttpClient().newBuilder()
                .build()
    }

    @Provides
    @Singleton
    fun provideOmdbApi(okHttpClient: OkHttpClient, gson: Gson) : OmdbApi {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(OmdbApi::class.java)

    }
}