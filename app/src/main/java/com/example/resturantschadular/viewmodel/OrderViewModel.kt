package com.example.resturantschadular.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.resturantschadular.model.Meal
import java.util.LinkedList

class OrderViewModel : ViewModel() {

    private val _scheduledOrders = mutableStateListOf<Meal>()
    val scheduledOrders: List<Meal> get() = _scheduledOrders

    private val _selectedAlgorithm = mutableStateOf("")
    val selectedAlgorithm: String get() = _selectedAlgorithm.value

    private var mealMenu = mutableListOf<Meal>()

    private fun  firstComeFirstServe(): List<Meal> {
        val sortedMeals = mealMenu.sortedBy { it.arrivalTime }
        Log.d("OrderViewModel-firstComeFirstServe",""+sortedMeals)
        return sortedMeals

    }
    private fun shortestJobNext(): List<Meal>{
        val sortedMeals = mealMenu.sortedBy { it.prepTime }
        Log.d("OrderViewModel-shortestJobNext",""+sortedMeals)

        return sortedMeals
    }
    private fun priorityFirst():List<Meal>{
        val sortedMeals =  mealMenu.sortedWith(compareByDescending<Meal>{it.priority}.thenBy { it.arrivalTime })
        Log.d("OrderViewModel-priorityFirst",""+sortedMeals)
        return sortedMeals
    }
    private fun roundRobin(timeQuantum:Int):List<Meal>{
        val queue = LinkedList(mealMenu) //FIFO
        val executionLog = mutableListOf<Meal>() // to store the sequence of how orders are processed
        var currentTime =0

        while (queue.isNotEmpty()){
            val order = queue.poll() // Takes the first order from the queue for processing
            if(order.prepTime > timeQuantum){
                executionLog.add(order.copy(prepTime = order.prepTime-timeQuantum))
                queue.offer(order.copy(prepTime =  order.prepTime - timeQuantum))
            }else{
                executionLog.add(order.copy(prepTime =  order.prepTime))
            }
            currentTime += timeQuantum
        }
        return executionLog
    }
    fun scheduleOrders(algorithm : String, orders:List<Meal>){

        _scheduledOrders.clear()
        _selectedAlgorithm.value =""
        mealMenu = orders.toMutableList()
        val sortedOrders =when (algorithm){
            "FCFS" -> firstComeFirstServe()
             "SJN" -> shortestJobNext()
            "Round Robin"-> roundRobin(2)
            "Priority Scheduling" -> priorityFirst()
            else -> emptyList()
        }
        _scheduledOrders.addAll(sortedOrders)
        _selectedAlgorithm.value=algorithm
        Log.d("OrderViewModel-scheduleOrders",""+sortedOrders)
        Log.d("OrderViewModel-scheduleOrders","algorithm: "+algorithm)


    }


}