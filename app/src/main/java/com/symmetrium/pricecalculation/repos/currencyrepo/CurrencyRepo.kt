package com.symmetrium.pricecalculation.repos.currencyrepo

import com.symmetrium.pricecalculation.models.CurrencyModel
import com.symmetrium.pricecalculation.network_module.ApiService

interface CurrencyRepo {
   suspend fun getCryptoCurrency(apiService: ApiService): CurrencyModel
}