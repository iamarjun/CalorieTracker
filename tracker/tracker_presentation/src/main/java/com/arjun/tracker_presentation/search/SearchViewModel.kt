package com.arjun.tracker_presentation.search

import com.arjun.core.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() :
    BaseViewModel<SearchContract.Event, SearchContract.State, SearchContract.Effect>() {
    override fun createInitialState(): SearchContract.State {
        TODO("Not yet implemented")
    }

    override fun handleEvent(event: SearchContract.Event) {
        TODO("Not yet implemented")
    }
}