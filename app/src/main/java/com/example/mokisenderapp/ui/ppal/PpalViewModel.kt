package com.example.mokisenderapp.ui.ppal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mokisenderapp.data.APIResult
import com.example.mokisenderapp.data.ppal.PpalRepository
import com.example.mokisenderapp.db.entities.Fact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PpalViewModel @Inject constructor(
    private val ppalRepository: PpalRepository
) : ViewModel() {

    private val _factInfo = MutableLiveData<APIResult<Fact>>()
    val factInfo: LiveData<APIResult<Fact>> = _factInfo

    fun getFact() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = ppalRepository.getFact()

            withContext(Dispatchers.Main) {
                if (result is APIResult.Success) {
                    _factInfo.value = result
                }
            }
        }
    }
}