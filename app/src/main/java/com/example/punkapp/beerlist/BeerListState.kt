package com.example.punkapp.beerlist

sealed class BeerListState {

    companion object {

        fun loading() = BeerListLoadingState

        fun error() = BeerListErrorState

        fun loaded(beerList: List<BeerListItem>) = BeerListLoadedState(beerList)
    }
}

data class BeerListLoadedState(val beerList: List<BeerListItem>) : BeerListState()

object BeerListLoadingState : BeerListState()

object BeerListErrorState : BeerListState()