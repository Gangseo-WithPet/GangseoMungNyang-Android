package com.jiwondev.withpet.data.repository

import com.google.firebase.database.GenericTypeIndicator
import com.jiwondev.withpet.data.datasourece.MapDatasource
import com.jiwondev.withpet.data.datasourece.NoticeDatasource
import com.jiwondev.withpet.model.MapDtoItem
import com.jiwondev.withpet.model.NoticeDto
import kotlinx.coroutines.flow.map

class NoticeRepository(private val datasource: NoticeDatasource) {
    val noticeResult = datasource.getNoticeResponse().map {
        it.getValue(object : GenericTypeIndicator<ArrayList<NoticeDto>>() {})
    }
}