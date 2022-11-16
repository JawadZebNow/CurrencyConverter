package com.symmetrium.pricecalculation.network_module

import com.symmetrium.pricecalculation.models.CurrencyModel
import retrofit2.Response
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(val apiService: ApiService) : ApiService {
    override suspend fun getCurrencies(): Response<CurrencyModel> {
        return apiService.getCurrencies()
    }

}