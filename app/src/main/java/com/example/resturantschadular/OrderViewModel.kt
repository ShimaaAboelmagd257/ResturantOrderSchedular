package com.example.resturantschadular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.LinkedList

class OrderViewModel : ViewModel() {

    private val _schaduledOrders = MutableLiveData<List<Order>>()
    val schadualedOeders: LiveData<List<Order>> = _schaduledOrders

    private  val orderList = mutableListOf<Order>()

    private fun  firstComeFirstServe(orders: List<Order>){
        orders.sortedBy { it.arrivalTime }
    }
    private fun shortestJobFirst(orders : List<Order>){
        orders.sortedBy { it.prepTime }
    }
    private fun priorityFirst(orders: List<Order>){
        orders.sortedWith(compareByDescending<Order>{it.priority}.thenBy { it.arrivalTime })
    }
    private fun roundRobin(orders: List<Order>, timeQuantum:Int):List<Order>{
        val queue = LinkedList(orders) //FIFO
        val executionLog = mutableListOf<Order>() // to store the sequence of how orders are processed
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


}