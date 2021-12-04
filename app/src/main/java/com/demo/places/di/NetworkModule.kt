package com.demo.places.di

import android.content.Context
import com.demo.places.BuildConfig
import com.demo.places.data.datasource.network.IPlacesNetworkDataSource
import com.demo.places.data.datasource.network.service.PlacesService
import com.demo.places.data.datasource.network.PlacesNetworkDataSource
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideHttpClient(androidContext()) }
    single { provideRetrofit(get()) }
    single { get<Retrofit>().create(PlacesService::class.java) }
    single<IPlacesNetworkDataSource> { PlacesNetworkDataSource(get()) }
}

private fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.PLACES_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

private fun provideHttpClient(context: Context): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(ChuckInterceptor(context))
        .addInterceptor(apiAuthInterceptor)
        .build()
}

private val apiAuthInterceptor = Interceptor{
    var request = it.request()
    val url =
        request.url.newBuilder().addQueryParameter("key", BuildConfig.GOOGLE_API_KEY).build()
    request = request.newBuilder().url(url).build()
    it.proceed(request)
}