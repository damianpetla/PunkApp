package com.example.punkapp.beerdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.request.ImageRequest
import dev.chrisbanes.accompanist.coil.CoilImage
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BeerDetailsFragment : Fragment() {

    private val args: BeerDetailsFragmentArgs by navArgs()
    private val viewModel: BeerDetailsViewModel by viewModel { parametersOf(args.beerId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                BeerDetailPage(viewModel = viewModel)
            }
        }
    }

    @Composable
    fun BeerDetailPage(viewModel: BeerDetailsViewModel) {
        val beer = viewModel.state.observeAsState().value
        if (beer != null) {
            ScrollableColumn(Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CoilImage(
                        request = ImageRequest.Builder(requireContext())
                            .data(beer.imageUrl)
                            .size(400)
                            .build(),
                        modifier = Modifier.preferredHeight(200.dp)
                    )
                    Text(text = "${beer.abv} %", style = MaterialTheme.typography.h3)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = beer.name, style = MaterialTheme.typography.h6)
                Header(name = "Methods")
                for (method in beer.methods) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = method.name, style = MaterialTheme.typography.body1)
                        method.temp?.let {
                            Text(text = it.toString(), style = MaterialTheme.typography.body1)
                        }
                    }
                }
                Header(name = "Hops")
                for (hop in beer.hops) {
                    Text(text = hop, style = MaterialTheme.typography.body1)
                }
                Header(name = "Malts")
                for (malt in beer.malts) {
                    Text(text = malt, style = MaterialTheme.typography.body1)
                }
            }
        } else {
            Box(Modifier.fillMaxSize(), gravity = ContentGravity.Center) {
                Text(text = "Hey, where is my beer?", fontSize = 24.sp)
            }
        }
    }

    @Composable
    fun Header(name: String) {
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.body1.copy(textDecoration = TextDecoration.Underline)
            )
        }
    }

}