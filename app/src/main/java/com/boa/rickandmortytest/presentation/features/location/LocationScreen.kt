package com.boa.rickandmortytest.presentation.features.location

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.boa.rickandmortytest.domain.model.LocationModel
import com.boa.rickandmortytest.presentation.component.AppText
import com.boa.rickandmortytest.presentation.component.ConnectivityStatus
import com.boa.rickandmortytest.presentation.component.InfoDialog
import com.boa.rickandmortytest.presentation.component.LoadingView
import com.boa.rickandmortytest.presentation.theme.PrimaryColor
import com.boa.rickandmortytest.presentation.theme.RickAndMortyTestTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber


@Composable
fun LocationScreen(
    modifier: Modifier = Modifier,
    viewModel: LocationViewModel = hiltViewModel()
) {
    val loadingState = viewModel.locationState.loadingState.collectAsStateWithLifecycle()
    val errorState = viewModel.locationState.errorState.collectAsStateWithLifecycle()
    val locationList = viewModel.locationState.locationList

    //Checking internet connection
    ConnectivityStatus { isConnected ->
        viewModel.updateConnectionStatus(isConnected)
    }

    //Display error dialog if needed
    if (errorState.value.isNotBlank()) {
        InfoDialog(
            title = "No connection?",
            description = errorState.value,
            onDismiss = {
                viewModel.refreshError("")
            }
        )
    }

    //Show loading indicator while fetching data
    LoadingView(loadingState) {
        viewModel.refreshLoading(false)
    }

    //Display location list
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LocationList(
            viewModel = viewModel,
            locations = locationList,
            onLocationClicked = { location ->
                Timber.d("Location: ${location.name}")
            })
    }
}

@Composable
fun LocationList(
    modifier: Modifier = Modifier,
    onLocationClicked: (LocationModel) -> Unit = {},
    locations: Flow<PagingData<LocationModel>>,
    viewModel: LocationViewModel? = null,
) {
    val locationList = locations.collectAsLazyPagingItems()

    if (locationList.itemCount == 0) {
        LaunchedEffect(true) {
            viewModel?.refreshError("No data to display. Please restart your connection or your app to continue.\n")
        }
    } else {
        LazyColumn(
            modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(locationList.itemCount) { index ->
                locationList[index]?.let {
                    LocationItem(it, onLocationClicked)
                }
            }
        }
        LaunchedEffect(true) {
            viewModel?.refreshError("")
            viewModel?.refreshLoading(false)
        }
    }
}

@Composable
fun LocationItem(location: LocationModel, onLocationClicked: (LocationModel) -> Unit = {}) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { if (location.residentUrls.isNotEmpty()) onLocationClicked(location) }
        .padding(4.dp)
        .border(2.dp, PrimaryColor, shape = RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = location.name.capitalize(Locale.current),
                textAlign = TextAlign.Start,
                minLines = 1,
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = Color.Black,
                maxLines = 2,
                fontWeight = FontWeight.Medium,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            AppText(text = location.dimension.capitalize(Locale.current))
            AppText(text = location.type.capitalize(Locale.current))
            AppText(
                text = "Residents #${location.residentUrls.count()}",
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LocationItemPreview() {
    var id = 0
    val location = LocationModel(
        id, "name", "type",
        "dimension", setOf("1", "2", "3"), "url", "created"
    )
    RickAndMortyTestTheme {
        val locationList = mutableListOf(location)
        repeat(10) {
            locationList.add(location.copy(id = ++id))
        }

        Column(
            modifier = Modifier.padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LocationList(
                locations = flowOf(PagingData.from(locationList.toList()))
            )
        }

    }
}