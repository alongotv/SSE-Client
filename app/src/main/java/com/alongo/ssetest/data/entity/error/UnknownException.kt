package com.alongo.ssetest.data.entity.error

class UnknownException: Exception() {
    override val message: String
        get() = "An unknown exception has been thrown."
}
