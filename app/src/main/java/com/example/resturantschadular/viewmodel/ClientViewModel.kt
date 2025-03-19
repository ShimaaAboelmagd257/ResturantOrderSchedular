package com.example.resturantschadular.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.resturantschadular.model.Client
import com.example.resturantschadular.model.Meal
import com.example.resturantschadular.utl.getCurrentTime

class ClientViewModel :ViewModel() {
    private val _clientsOrders = mutableStateListOf<Client>()
    val clientsOrders: List<Client> get() = _clientsOrders
    private var timeTracker = getCurrentTime()
    fun addClientOrder(meal: Meal) {
        _clientsOrders.clear()
        val startTime = timeTracker
        val endTime = startTime + (meal.prepTime * 1000L) // Convert minutes to milliseconds
        timeTracker = endTime // Update for the next meal

        val reaction = getClientReaction(meal.prepTime, endTime)

        val clientOrder = Client(
            meal = meal,
            reaction = reaction
        )

        _clientsOrders.add(clientOrder)
        Log.d("ClientViewModel", "Added Client Order: $clientOrder")
    }



    fun getClientReaction(prepTime:Int,servedTime : Long):String{
        Log.d("ClientViewModel-getClientReaction","--------")
        val timeTaken = (servedTime- getCurrentTime()) /1000
        Log.d("ClientViewModel-getClientReaction"," timeTaken: "+timeTaken)

        return when{
            timeTaken <= prepTime + 2 ->"happy"
            timeTaken in (prepTime+3)..(prepTime + 6) -> "sad"
            else -> "angry"
         }
    }

}