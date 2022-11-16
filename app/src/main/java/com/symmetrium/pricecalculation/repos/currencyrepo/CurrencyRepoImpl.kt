package com.symmetrium.pricecalculation.repos.currencyrepo

import com.symmetrium.pricecalculation.models.CurrencyModel
import com.symmetrium.pricecalculation.network_module.ApiService
import okhttp3.internal.wait


class CurrencyRepoImpl : CurrencyRepo {

    override suspend fun getCryptoCurrency(apiService: ApiService): CurrencyModel {


        val currencies = apiService.getCurrencies()
        currencies.body()?.let {
            if (currencies.isSuccessful) {
                return it
            }
        }
        return CurrencyModel(HashMap<String, String>())
    }
}