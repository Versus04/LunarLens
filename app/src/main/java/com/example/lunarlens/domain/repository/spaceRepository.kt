package com.example.lunarlens.domain.repository

import com.example.lunarlens.data.remote.api.apodApi
import com.example.lunarlens.data.remote.api.apodService
import com.example.lunarlens.data.remote.api.planetservice
import com.example.lunarlens.data.remote.dto.apodDTO
import com.example.lunarlens.data.remote.dto.planetDTO
import retrofit2.Response

interface spaceRepository {
    suspend fun getapod() : Response<apodDTO>
}
class networkSpaceRepostiory() : spaceRepository
{
    override suspend fun getapod(): Response<apodDTO> {
        return apodService.apodservice.getapod()
    }


}