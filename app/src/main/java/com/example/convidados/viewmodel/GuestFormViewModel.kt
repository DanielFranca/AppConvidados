package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devmasterteam.convidados.service.repository.GuestRepository
import com.example.convidados.model.GuestModel
import com.example.convidados.model.SuccessFailure


class GuestFormViewModel(application: Application): AndroidViewModel(application) {

    private val repository = GuestRepository(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<SuccessFailure>()
    val saveGuest: LiveData<SuccessFailure> = _saveGuest

    fun save(guest: GuestModel) {
        val successFailure = SuccessFailure(true, "")
        if (guest.id == 0) {
            successFailure.succes = repository.save(guest)
        } else {
           successFailure.succes = repository.update(guest)
        }
    }
    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }
}