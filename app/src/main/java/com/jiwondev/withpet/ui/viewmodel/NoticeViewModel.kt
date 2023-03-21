package com.jiwondev.withpet.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jiwondev.withpet.data.repository.MapRepository
import com.jiwondev.withpet.data.repository.NoticeRepository

class NoticeViewModel(private val noticeRepository: NoticeRepository) : ViewModel() {
    val noticeFlow = noticeRepository.noticeResult
}


class NoticeViewModelFactory(private val param: NoticeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(NoticeViewModel::class.java)) {
            NoticeViewModel(param) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}