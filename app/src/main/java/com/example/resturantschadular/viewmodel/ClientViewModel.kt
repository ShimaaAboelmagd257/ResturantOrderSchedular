package com.example.resturantschadular.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.example.resturantschadular.model.Client
import com.example.resturantschadular.model.Meal
import com.example.resturantschadular.utl.getCurrentTime

class ClientViewModel :ViewModel() {
   /* private val _clientsOrders = mutableStateListOf<Client>()
    val clientsOrders: List<Client> get() = _clientsOrders
  //  private var timeTracker = getCurrentTime()
    private val _clientReactions = mutableStateMapOf<String, String>()
    val clientReactions: Map<String, String> get() = _clientReactions

    fun addClientOrder(meal: Meal) {
        val reaction = getClientReaction(meal.prepTime, meal.servedTime, meal.arrivalTime)
        _clientReactions[meal.name] = reaction // ✅ Store reaction once
        Log.d("Res/ClientViewModel", "Client Reaction: $reaction for meal ${meal.name}")
    }
   *//* fun addClientOrder(meal: Meal) {
    //    _clientsOrders.clear()
     //   val reaction = getClientReaction(meal.prepTime,meal.servedTime)
        //Log.d("ClientViewModel", " Client Reaction: $reaction for meal ${meal.name}")

        val clientOrder = Client(
            meal = meal,
            reaction = reaction
        )

        _clientsOrders.add(clientOrder)
        Log.d("ClientViewModel", "Added Client Order: $clientOrder")
    }*/

    private fun getClientReaction(prepTime: Int, servedTime: Long, arrivalTime: Long): String {
      //  if (servedTime == 0L || arrivalTime == 0L || servedTime < arrivalTime) return "⏳ Waiting"

        val waitTimeMillis = servedTime - arrivalTime
        val waitTimeSec = waitTimeMillis / 1000.0
        val prepTimeMillis = prepTime  * 1000L

        Log.d("Res/ClientViewModel-getClientReaction", "waitTime = ${waitTimeSec} sec (${waitTimeMillis} ms)")

        return when {
            waitTimeMillis <= prepTimeMillis -> "happy"
            waitTimeMillis <= prepTimeMillis+15_000 -> "sad" //
            else -> "angry"
        }
    }




}