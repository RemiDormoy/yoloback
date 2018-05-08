package com.octo.remi.yoloback.utils

fun <T>MutableList<T>.addAllIf(secondList: List<T>, predicate : (T) -> Boolean) {
    secondList.forEach { this.addIf(it, predicate) }
}

fun <T>MutableList<T>.addIf(item: T, predicate : (T) -> Boolean) {
    if (predicate(item)) {
        this.add(item)
    }
}