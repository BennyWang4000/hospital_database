package com.example.hospital_database.di

import com.example.hospital_database.network.ApiRepository
import com.example.hospital_database.network.ApiService
import com.example.hospital_database.utils.Config
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module{
    single { provideOkHttpClient() }
    single { provideApiService(get()) }
    single { provideApiRepository(get()) }
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
//        .connectTimeout(30, TimeUnit.SECONDS)
//        .readTimeout(60, TimeUnit.SECONDS)
//        .writeTimeout(60, TimeUnit.SECONDS)
//        .addInterceptor(authInterceptor)
//        .addInterceptor(httpLoggingInterceptor)
        .build()
}


fun provideApiService(okHttpClient: OkHttpClient): ApiService {
    val contentType = "application/json".toMediaType()

    return Retrofit.Builder()
        .baseUrl(Config.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ApiService::class.java)

}



fun provideApiRepository(apiService: ApiService): ApiRepository {
    return ApiRepository(apiService)
}
