package com.anandgaur.techugotask.ui.details

import com.anandgaur.techugotask.constants.Constants
import com.anandgaur.techugotask.repository.responseModel.OffersResponse


class DetailsRepository {

    /*request to get the details form api*/

    fun getDetailsResponse(): OffersResponse? =
        Constants.mOffersResponse


}
