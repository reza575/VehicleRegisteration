package com.moeiny.reza.vehicleregisteration.data.model.apimodel

import com.moeiny.reza.vehicleregisteration.ui.detailsinfo.DetailsViewModel
import com.nhaarman.mockito_kotlin.mock
import junit.framework.TestCase
import org.junit.Assert.assertNotEquals

class DetailsViewModelTest : TestCase(){
    fun  `testConvertDate correct date` () {

        val registration1 = mock<RegistrationX> {
            on { expiryDate }.then { "2020-03-01T23:15:30.000Z" }
        }

        val viewModel = DetailsViewModel(mock())
        assertEquals("01-mar-2020", viewModel.convertDate(registration1.expiryDate!!))

    }

    fun  `testConvertDate wrong date` () {

        val registration1 = mock<RegistrationX> {
            on { expiryDate }.then { "2021-02-05T23:15:30.000Z" }
        }

        val viewModel = DetailsViewModel(mock())
        assertNotEquals("05-apr-2021", viewModel.convertDate(registration1.expiryDate!!))

    }

    fun  `testValidateMonth expiryDate lower than currentDate`() {

        val registration1 = mock<RegistrationX> {
            on { expiryDate }.then { "2020-03-01T23:15:30.000Z" }
        }

        val viewModel = DetailsViewModel(mock())
        assertEquals("0 Month", viewModel.validateMonth(registration1.expiryDate!!))
    }

    fun  `testValidateMonth expiryDate higher than currentDate`() {

        val registration1 = mock<RegistrationX> {
            on { expiryDate }.then { "2021-03-01T23:15:30.000Z" }
        }

        val viewModel = DetailsViewModel(mock())
        assertEquals("3 Month(s)", viewModel.validateMonth(registration1.expiryDate!!))

    }

    fun  `testValidateMonth expiryDate equal to currentDate`() {

        val registration1 = mock<RegistrationX> {
            on { expiryDate }.then { "2020-12-01T23:15:30.000Z" }
        }

        val viewModel = DetailsViewModel(mock())
        assertEquals("0 Month", viewModel.validateMonth(registration1.expiryDate!!))

    }

}