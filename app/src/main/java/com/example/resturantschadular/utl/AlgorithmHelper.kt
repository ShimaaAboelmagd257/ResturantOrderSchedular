package com.example.resturantschadular.utl

import com.example.resturantschadular.R
import com.example.resturantschadular.model.Algorithm

fun getAlgorithms(): List<Algorithm> {
    return listOf(
        Algorithm(name = "FCFS", icon = R.drawable.fcfs),
        Algorithm(name = "SJN", icon = R.drawable.sjf),
        Algorithm(name = "Round Robin", icon = R.drawable.roundrobin),
        Algorithm(name = "Priority Scheduling", icon = R.drawable.priority)
    )
}
