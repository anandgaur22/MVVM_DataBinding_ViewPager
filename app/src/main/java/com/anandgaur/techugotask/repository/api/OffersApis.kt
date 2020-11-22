package com.anandgaur.techugotask.repository.api

import com.anandgaur.techugotask.constants.Constants.GET_OFFERS_ENDPOINTS
import com.anandgaur.techugotask.repository.responseModel.OffersResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface OffersApis {
    @GET(GET_OFFERS_ENDPOINTS)
    fun getOffers(): Observable<Response<OffersResponse>>

}

