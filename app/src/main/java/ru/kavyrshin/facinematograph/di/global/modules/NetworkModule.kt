package ru.kavyrshin.facinematograph.di.global.modules

import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.kavyrshin.facinematograph.data.network.OmdbApi
import ru.kavyrshin.facinematograph.di.global.modules.Companion.BASE_URL


val networkModule = module {
    single { provideGson() }
    single { provideOkHttp() }
    single { provideOmdbApi(get(), get()) }
}

object Companion {
    const val BASE_URL : String = "http://www.omdbapi.com/"
}

fun provideOmdbApi(okHttpClient: OkHttpClient, gson: Gson) : OmdbApi {
    return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(OmdbApi::class.java)

}

fun provideGson() : Gson {
    return Gson()
}

fun provideOkHttp() : OkHttpClient {
    return OkHttpClient().newBuilder()
            .build()
}