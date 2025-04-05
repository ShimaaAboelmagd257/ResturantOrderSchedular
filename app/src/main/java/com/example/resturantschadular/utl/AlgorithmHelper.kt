package com.example.resturantschadular.utl

import com.example.resturantschadular.R
import com.example.resturantschadular.model.Algorithm

fun getAlgorithms(): List<Algorithm> {
    return listOf(
        Algorithm(name = "FCFS", icon = R.drawable.sandclock),
        Algorithm(name = "SJN", icon = R.drawable.flash),
        Algorithm(name = "Round Robin", icon = R.drawable.switching),
        Algorithm(name = "Priority Scheduling", icon = R.drawable.vip)
    )
}
