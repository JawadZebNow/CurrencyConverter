package com.symmetrium.pricecalculation.ui.activities.mainactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.symmetrium.pricecalculation.models.CurrencyModel
import com.symmetrium.pricecalculation.network_module.ApiService
import com.symmetrium.pricecalculation.network_module.ApiServiceImpl
import com.symmetrium.pricecalculation.repos.currencyrepo.CurrencyRepo
import com.symmetrium.pricecalculation.repos.currencyrepo.CurrencyRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val currencyRepo: CurrencyRepo,
    private val apiService: ApiServiceImpl
) : ViewModel() {

    private lateinit var currencyModel: CurrencyModel
    val allCurrencies = MutableLiveData<HashMap<String, String>>()

    fun loadCurrencies() {
        viewModelScope.launch {
            currencyModel = currencyRepo.getCryptoCurrency(apiService)
            allCurrencies.value = currencyModel.symbols
        }
    }

}