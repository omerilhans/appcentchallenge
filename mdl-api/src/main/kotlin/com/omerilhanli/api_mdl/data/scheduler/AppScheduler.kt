package com.omerilhanli.api_mdl.data.scheduler

import io.reactivex.Scheduler

interface AppScheduler {

    fun io(): Scheduler

    fun main(): Scheduler
}