package com.example.lunarlens.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunarlens.data.remote.api.apodService
import com.example.lunarlens.data.remote.api.planetservice
import com.example.lunarlens.data.remote.api.search
import com.example.lunarlens.data.remote.dto.Collection
import com.example.lunarlens.data.remote.dto.Item
import com.example.lunarlens.data.remote.dto.Link
import com.example.lunarlens.data.remote.dto.LinkX
import com.example.lunarlens.data.remote.dto.MarsDTO
import com.example.lunarlens.data.remote.dto.Metadata
import com.example.lunarlens.data.remote.dto.Photo
import com.example.lunarlens.data.remote.dto.SearchDTO
import com.example.lunarlens.data.remote.dto.apodDTO
import com.example.lunarlens.data.remote.dto.planetDTOItem
import com.example.lunarlens.domain.repository.networkSpaceRepostiory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class homeScreenViewmodel : ViewModel()
{

    private val _marsImage = MutableStateFlow<List<Photo>>(emptyList())
    val  marsImage  : StateFlow<List<Photo>> = _marsImage.asStateFlow()
    val emptyitems : List<Item> = emptyList()
    val emptylinks : List<LinkX> = emptyList()
    private val emptysearch= SearchDTO(
        collection = Collection(
            href = "TODO()",
            items = emptyitems,
            links = emptylinks,
            metadata = Metadata(1),
            version ="1"
        )
    )
    private val emptyCollection = Collection(
        href = "TODO()",
        items = emptyitems,
        links = emptylinks,
        metadata = Metadata(1),
        version ="1"
    )
    private val _searchResult = MutableStateFlow<Collection>(emptyCollection)
    val searchResult : StateFlow<Collection> = _searchResult.asStateFlow()
    
    private val _searchField = MutableStateFlow<String>("")
    val searchField : StateFlow<String> = _searchField.asStateFlow()
    private val _showBottomBar = MutableStateFlow<Boolean>(true)
    val showBottomBar : StateFlow<Boolean> = _showBottomBar.asStateFlow()
    val planets =
        listOf<String>("Mercury","Venus",
            "Earth", "Mars", "Jupiter", "Saturn",
            "Uranus",  "Neptune")
    private val _planetsdata = MutableStateFlow<List<planetDTOItem>>(emptyList())
    val planetdata : StateFlow<List<planetDTOItem>> = _planetsdata.asStateFlow()
    val spaceRepository = networkSpaceRepostiory()
    val emptyapod = apodDTO(
        copyright = "TODO()",
        date = "TODO",
        explanation = "",
        hdurl = "TODO()",
        media_type = "TODO()",
        service_version = "TODO()",
        title = "",
        url = "TODO()"
    )
    private val _apod = MutableStateFlow<apodDTO>(emptyapod)
    val apod : StateFlow<apodDTO> = _apod.asStateFlow()

    fun getapod()
    {
        viewModelScope.launch(){
            try {
                val response = spaceRepository.getapod()
                response.body()?.let {apoddata ->
                        _apod.value=apoddata
                    }
            }
            catch (e: Exception)

            {
                Log.d("nasa",e.toString())
            }

        }
    }
    fun setBottomBar(visible : Boolean)
    {
        _showBottomBar.value = visible
        Log.d("visibility" ,_showBottomBar.value.toString())
    }
    fun updateSearch(search : String)
    {
        _searchField.value = search
    }
    fun getplanets() {
        viewModelScope.launch {
            try {
                planets.forEach { planetName ->
                    val response = planetservice.api.getPlanets(planetName)
                    response.body()?.let { planetData ->
                        _planetsdata.value = _planetsdata.value + planetData
                    }
                }
            } catch (e: Exception) {
                Log.d("planet", e.toString())
            }
        }
    }
    fun getSearchResult()
    {
        viewModelScope.launch()
        {
            val response = search.searchService.getResult(_searchField.value)
            response.body()?.let { response ->
                _searchResult.value = response.collection
                Log.d("kyamila" , response.collection.items.size.toString())
            }
        }
    }
    fun getMarsphotos()
    {
        viewModelScope.launch()
        {
            try{
                val response = apodService.apodservice.getMarsImages()
                response.body()?.let { photo ->
                    _marsImage.value = photo.photos
                    Log.d("imgsrc" ,marsImage.value.size.toString())
                }
            }
            catch (e: Exception)
            {
                Log.d("nasawaale" ,e.toString())
            }
        }
    }
init {
    getapod()
    //getplanets()
    getMarsphotos()

}
}