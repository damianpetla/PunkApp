package com.example.punkapp.beerlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.request.ImageRequest
import dev.chrisbanes.accompanist.coil.CoilImage
import org.koin.android.viewmodel.ext.android.viewModel


class BeerListFragment : Fragment() {

    private val viewModel by viewModel<BeerListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    BeerListPage(viewModel)
                }
            }
        }
    }

    @Composable
    fun BeerListPage(viewModel: BeerListViewModel) {
        val state = viewModel.state.observeAsState()
        when (val loadingState = state.value) {
            is BeerListLoadingState -> {
                Box(Modifier.fillMaxSize(), gravity = ContentGravity.Center) {
                    CircularProgressIndicator()
                }
            }
            is BeerListLoadedState -> {
                val beers = loadingState.beerList
                LazyColumnFor(
                    items = beers,
                ) { beer ->
                    BeerItem(beer) {
                        val action = BeerListFragmentDirections.openDetails(beer.id)
                        findNavController().navigate(action)
                    }
                }
            }
            BeerListErrorState -> {
                Box(Modifier.fillMaxSize(), gravity = ContentGravity.Center) {
                    Text(text = "Huston, we have a problem!", fontSize = 24.sp)
                }
            }
        }
    }

    @Composable
    fun BeerItem(beer: BeerListItem, onClick: () -> Unit) {
        Row(Modifier.padding(16.dp).clickable(onClick = onClick).fillMaxWidth()) {
            CoilImage(
                request = ImageRequest.Builder(requireContext())
                    .data(beer.imageUrl)
                    .size(200)
                    .build(),
                modifier = Modifier.preferredHeight(80.dp).preferredWidth(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = beer.name, style = MaterialTheme.typography.h6)
                Text(text = "${beer.abv} %", style = MaterialTheme.typography.subtitle1)
            }
        }
    }
}