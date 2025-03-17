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

    fun addClientOrder(meal: Meal){
        _clientsOrders.clear()
        val servedTime = getCurrentTime() + meal.prepTime
        val reaction = getClientReaction(meal.prepTime,servedTime)
        Log.d("ClientViewModel-addClientOrder","ADD "+meal.name)

        _clientsOrders.add(Client(meal,reaction))
    }



    fun getClientReaction(prepTime:Int,servedTime : Int):String{
        Log.d("ClientViewModel-getClientReaction","--------")
        return when{
            servedTime <= prepTime + 2 ->"happy"
            servedTime in (prepTime+3)..(prepTime + 6) -> "sad"
            else -> "angry"
         }
    }

}