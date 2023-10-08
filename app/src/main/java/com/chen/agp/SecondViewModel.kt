package com.chen.agp

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.chen.base_bean.HttpResult
import com.chen.base_bean.MusicDetail
import com.chen.base_http.business.MusicMediaMethod
import com.chen.base_utils.KLog
import com.chen.base_view.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class SecondViewModel(applicaiton:Application):BaseViewModel(applicaiton) {
    private   val TAG = "SecondViewModel"
    // Expose screen UI state
    private val _uiState = MutableStateFlow(DiceUiState())
    val uiState: StateFlow<DiceUiState> = _uiState.asStateFlow()

    // Handle business logic
    fun rollDice() {
        _uiState.update { currentState ->
            currentState.copy(
                firstDieValue = Random.nextInt(from = 1, until = 7),
                secondDieValue = Random.nextInt(from = 1, until = 7),
                numberOfRolls = currentState.numberOfRolls + 1,
            )
        }
    }
    public fun refreshDatas(){
        viewModelScope.launch(Dispatchers.Main){
            KLog.d(TAG,"startToGetNewData")
            withContext(Dispatchers.IO){

            }
        }
    }
    /**
     * 获取某个音乐Id 的详细信息
     */
    fun getMusicDetail(id:String?){
        viewModelScope.async (Dispatchers.IO){
          val httpresult:HttpResult<MusicDetail?>? =
              MusicMediaMethod.instance.getService().getMusicDetail(id)

            KLog.d(TAG,"getMusicDetail:${httpresult}");
        }
    }
    override fun onCleared() {
        super.onCleared()
    }
}

data class DiceUiState(
    val firstDieValue: Int? = null,
    val secondDieValue: Int? = null,
    val numberOfRolls: Int = 0,
)