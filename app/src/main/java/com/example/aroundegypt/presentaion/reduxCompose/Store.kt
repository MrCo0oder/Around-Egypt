package com.example.aroundegypt.presentaion.reduxCompose

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Store<S>(
    initialState: S,
    private val reducer: Reducer<S>
) {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    fun dispatch(action: Action) {
        _state.value = reducer(_state.value, action)
    }
}
