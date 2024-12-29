package com.example.lunarlens.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunarlens.data.remote.api.apodService
import com.example.lunarlens.data.remote.api.planetservice
import com.example.lunarlens.data.remote.dto.apodDTO
import com.example.lunarlens.data.remote.dto.planetDTOItem
import com.example.lunarlens.domain.repository.networkSpaceRepostiory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class homeScreenViewmodel : ViewModel()
{
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
init {
    getapod()
    //getplanets()
}
}