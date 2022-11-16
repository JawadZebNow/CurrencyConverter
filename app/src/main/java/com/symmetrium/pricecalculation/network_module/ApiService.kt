package com.symmetrium.pricecalculation.network_module

import com.symmetrium.pricecalculation.models.CurrencyModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("fixer/symbols")
    suspend fun getCurrencies(): Response<CurrencyModel>
}