package com.symmetrium.pricecalculation.di

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.symmetrium.pricecalculation.BuildConfig
import com.symmetrium.pricecalculation.network_module.ApiService
import com.symmetrium.pricecalculation.repos.currencyrepo.CurrencyRepo
import com.symmetrium.pricecalculation.repos.currencyrepo.CurrencyRepoImpl
import com.symmetrium.pricecalculation.ui.activities.mainactivity.MainActivity
import com.symmetrium.pricecalculation.ui.activities.mainactivity.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset
import java.util.*
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModules {
    @Provides
    @Singleton
    fun provideCryptocurrencyRepository(): CurrencyRepo = CurrencyRepoImpl()


    @Provides
    fun provideBaseUrl() = BuildConfig.WEB_SERVER_URL


    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.addHeader("Content-Type", "application/json")
                builder.addHeader("apikey", "06ddaZM8SQAo3ZARPQ0xdkJ4YNXPsoZK")
                val request = builder.build()
                var requestSummary = "--> $request"
                val requestBody = request.body
                val UTF8 =
                    Charset.forName("UTF-8")
                if (requestBody != null) {
                    val buffer = Buffer()
                    requestBody.writeTo(buffer)
                    requestSummary = """
                        $requestSummary
                        --> Content-Type: application/json
                        --> ${buffer.readString(UTF8)}
                        """.trimIndent()
                }
                val response = chain.proceed(request)
                var responseSummary = String.format(
                    Locale.ENGLISH,
                    "<-- %s",
                    response.toString()
                )
                val responseBody = response.body
                if (responseBody != null) {
                    val source = responseBody.source()
                    source.request(Long.MAX_VALUE)
                    val buffer = source.buffer
                    responseSummary = """
                        $responseSummary
                        <-- ${buffer.clone().readString(UTF8)}
                        """.trimIndent()
                }
                response
            }
            .build()
    } else {
        OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.addHeader("Content-Type", "application/json")
                builder.addHeader("apikey", "06ddaZM8SQAo3ZARPQ0xdkJ4YNXPsoZK")
                val request = builder.build()
                var requestSummary = "--> $request"
                val requestBody = request.body
                val UTF8 =
                    Charset.forName("UTF-8")
                if (requestBody != null) {
                    val buffer = Buffer()
                    requestBody.writeTo(buffer)
                    requestSummary = """
                        $requestSummary
                        --> Content-Type: application/json
                        --> ${buffer.readString(UTF8)}
                        """.trimIndent()
                }
                val response = chain.proceed(request)
                var responseSummary = String.format(
                    Locale.ENGLISH,
                    "<-- %s",
                    response.toString()
                )
                val responseBody = response.body
                if (responseBody != null) {
                    val source = responseBody.source()
                    source.request(Long.MAX_VALUE)
                    val buffer = source.buffer
                    responseSummary = """
                        $responseSummary
                        <-- ${buffer.clone().readString(UTF8)}
                        """.trimIndent()
                }
                response
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


}