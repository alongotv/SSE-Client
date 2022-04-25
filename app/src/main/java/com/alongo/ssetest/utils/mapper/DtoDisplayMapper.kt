package com.alongo.ssetest.utils.mapper

abstract class DtoDisplayMapper<Dto, Display> {
    abstract fun toDisplay(obj: Dto): Display
}
