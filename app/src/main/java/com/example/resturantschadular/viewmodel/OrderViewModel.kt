package com.example.resturantschadular.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.resturantschadular.model.Meal
import com.example.resturantschadular.utl.getCurrentTime
import com.example.resturantschadular.utl.getMeals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.LinkedList

class OrderViewModel : ViewModel() {

    private val _scheduledOrders = MutableStateFlow<List<Meal>>(emptyList())
    val scheduledOrders: StateFlow<List<Meal>> = _scheduledOrders.asStateFlow()

    private val _selectedAlgorithm = mutableStateOf("")
    val selectedAlgorithm: String get() = _selectedAlgorithm.value

    private val _mealMenu = mutableStateListOf<Meal>()
    val mealMenu: List<Meal> get() = _mealMenu

    init {
        loadMeals()
    }

    private fun loadMeals() {
        _mealMenu.clear()
        _mealMenu.addAll(getMeals())
    }

    private fun roundRobin(meals: List<Meal>, timeQuantum: Int): List<Meal> {
        val queue = LinkedList(meals.map { it.copy(prepTime = it.prepTime) })
        val finalOrders = mutableListOf<Meal>()
        var currentTime = System.currentTimeMillis()
        val startTimes = mutableMapOf<String, Long>()

        while (queue.isNotEmpty()) {
            val meal = queue.poll()

            if (meal.name !in startTimes) {
                startTimes[meal.name] = currentTime
            }

            val executionTime = minOf(meal.prepTime, timeQuantum)
            val servedTime = currentTime + (executionTime * 1000L)
            val remainingTime = meal.prepTime - executionTime

            if (remainingTime > 0) {
                queue.offer(meal.copy(prepTime = remainingTime))
            } else {
                finalOrders.add(meal.copy(prepTime = 0, servedTime = servedTime, startTime = startTimes[meal.name] ?: currentTime))
            }

            currentTime = servedTime
        }

        return finalOrders
    }


   fun scheduleOrders(algorithm: String, selectedMeals: List<Meal>, clientViewModel: ClientViewModel) {

       var timeTracker = System.currentTimeMillis()
       val sortedOrders = when (algorithm) {
           "FCFS" -> selectedMeals.sortedBy { it.arrivalTime }
           "SJN" -> selectedMeals.sortedBy { it.prepTime }
           "Priority Scheduling" -> selectedMeals.sortedByDescending { it.priority }
           "Round Robin" -> roundRobin(selectedMeals, 2)
           else -> selectedMeals
       }
       val updatedMeals = sortedOrders.map { meal ->
           val startTime = maxOf(timeTracker, meal.arrivalTime) // ✅ Ensure order affects timing
           val servedTime = startTime + (meal.prepTime * 60*1000L)
           servedTime.also { timeTracker = it }
           meal.copy(servedTime = servedTime, startTime = startTime)
       }
      // updatedMeals.forEach { clientViewModel.addClientOrder(it) }
        _scheduledOrders.value = updatedMeals
       _selectedAlgorithm.value = algorithm
       Log.d("Res/OrderViewModel-scheduleOrders", "Final Meals: $sortedOrders")
   }


}

/*private fun  firstComeFirstServe(): List<Meal> {
      val sortedMeals = mealMenu.sortedBy { it.arrivalTime }
      Log.d("Res/OrderViewModel-firstComeFirstServe",""+sortedMeals)
      return sortedMeals

  }
  private fun shortestJobNext(): List<Meal>{
      val sortedMeals = mealMenu.sortedBy { it.prepTime }
      Log.d("Res/OrderViewModel-shortestJobNext",""+sortedMeals)

      return sortedMeals
  }
  private fun priorityFirst():List<Meal>{
      val sortedMeals =  mealMenu.sortedWith(compareByDescending<Meal>{it.priority}.thenBy { it.arrivalTime })
      Log.d("Res/OrderViewModel-priorityFirst",""+sortedMeals)
      return sortedMeals
  }*/