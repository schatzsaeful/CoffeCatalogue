package com.example.coffeecatalogue.base

import kotlinx.coroutines.Job

interface BaseViewModelContract {

    fun getSupervisorJob(): Job

}
