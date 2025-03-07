package com.example.resturantschadular.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.resturantschadular.model.Meal
import java.util.LinkedList

class OrderViewModel : ViewModel() {

    private val _scheduledOrders = mutableStateListOf<Meal>()
    val scheduledOrders: List<Meal> get() = _scheduledOrders
    private var orderList = mutableListOf<Meal>()

    private fun  firstComeFirstServe(): List<Meal> {
        return orderList.sortedBy { it.arrivalTime }

    }
    private fun shortestJobFirst(): List<Meal>{
        return orderList.sortedBy { it.prepTime }
    }
    private fun priorityFirst():List<Meal>{
        return orderList.sortedWith(compareByDescending<Meal>{it.priority}.thenBy { it.arrivalTime })
    }
    private fun roundRobin(timeQuantum:Int):List<Meal>{
        val queue = LinkedList(orderList) //FIFO
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
        orderList = orders.toMutableList()
        val sortedOrders =when (algorithm){
            "FSFS" -> firstComeFirstServe()
             "SJN" -> shortestJobFirst()
            "Round Robin"-> roundRobin(2)
            "Priority Scheduling" -> priorityFirst()
            else -> emptyList()
        }
        _scheduledOrders.addAll(scheduledOrders)
    }


}