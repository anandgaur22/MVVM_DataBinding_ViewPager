package com.anandgaur.techugotask.ui.offers

import com.anandgaur.techugotask.application.App
import com.anandgaur.techugotask.repository.responseModel.OffersResponse

import io.reactivex.Observable
import retrofit2.Response


class OffersRepository {

    /*request to get offers details form api*/

    fun getOffersApiResponse(): Observable<Response<OffersResponse>> =
        App.offersApi.getOffers()


}
