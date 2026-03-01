package com.example.ktcore

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class KtCoreMain {

}

class SerialQueue(
    dispatcher: CoroutineDispatcher = Dispatchers.Default.limitedParallelism(1)
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatcher)

    fun post(block: suspend () -> Unit): Job =
        scope.launch { block() }

    fun cancel() = scope.cancel()
}

fun main() {

}