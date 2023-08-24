package com.example.adhipcalendar.ui.monthView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adhipcalendar.models.AddTaskRequestBody
import com.example.adhipcalendar.usecase.AddTaskUseCase
import com.example.adhipcalendar.utils.constansts.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonthViewModel @Inject constructor(
    private val useCase: AddTaskUseCase
) : ViewModel() {

    private val _addTaskLiveData = MutableLiveData<Boolean>()
    private val _errorLiveData = MutableLiveData<Boolean?>()

    fun addTaskLiveData() = _addTaskLiveData

    fun addTask(body: AddTaskRequestBody) {
        viewModelScope.launch {
            useCase.invoke(body)
                .collect { response ->
                    when (response) {
                        is Result.Success -> {
                            _addTaskLiveData.postValue(true)
                        }

                        else -> {
                            _addTaskLiveData.postValue(false)
                            _errorLiveData.postValue(true)
                        }
                    }
                }
        }
    }
}