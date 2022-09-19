package com.svetikov.myflowtest

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow


class MyFlowModel : ViewModel() {

    val time = flow<Int> {
        val startTime = 10
        emit(startTime)
        var currentTime = startTime
        while (currentTime > 0) {

            delay(1000L)
            currentTime--
            Log.d("Time", "current time $currentTime")
            emit(currentTime)
        }
    }


    @JvmName("getTime1")
    suspend fun getTime(time:Int=0): Flow<Int>
// : Flow<Int>
    {
        return flow<Int> {
            Log.d("Time", "Button push into function")
            var v = 50
            emit(time)
            v = time
            while (v>0){
                delay(500L)
                v--
                Log.d("Time", "Button push into function  $v" )
                emit(v)
            }

        }

    }
}