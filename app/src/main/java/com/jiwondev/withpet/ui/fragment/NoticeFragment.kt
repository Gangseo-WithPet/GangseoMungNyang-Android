package com.jiwondev.withpet.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.jiwondev.withpet.R
import com.jiwondev.withpet.data.datasourece.MapDatasource
import com.jiwondev.withpet.data.datasourece.NoticeDatasource
import com.jiwondev.withpet.data.repository.MapRepository
import com.jiwondev.withpet.data.repository.NoticeRepository
import com.jiwondev.withpet.databinding.FragmentMapBinding
import com.jiwondev.withpet.databinding.FragmentNoticeBinding
import com.jiwondev.withpet.model.NoticeDto
import com.jiwondev.withpet.ui.adapter.NoticeAdapter
import com.jiwondev.withpet.ui.adapter.event.NoticeEvent
import com.jiwondev.withpet.ui.viewmodel.MapViewModel
import com.jiwondev.withpet.ui.viewmodel.MapViewModelFactory
import com.jiwondev.withpet.ui.viewmodel.NoticeViewModel
import com.jiwondev.withpet.ui.viewmodel.NoticeViewModelFactory
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NoticeFragment : BaseFragment<FragmentNoticeBinding>(FragmentNoticeBinding::inflate) {
    lateinit var noticeViewModel: NoticeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    noticeViewModel.noticeFlow.collectLatest { noticeItem ->
                        noticeItem?.let {
                            binding.noticeRecyclerView.bindingNoticeAdapter(noticeItem)
                        } ?: Toast.makeText(requireActivity(), "등록된 공지가 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun init() {
        noticeViewModel = ViewModelProvider(this,
        NoticeViewModelFactory(NoticeRepository(NoticeDatasource()))
        )[NoticeViewModel::class.java]
    }

    private fun RecyclerView.bindingNoticeAdapter(noticeArrayList: ArrayList<NoticeDto>) {
        val noticeAdapter = NoticeAdapter(noticeArrayList)
        noticeAdapter.setOnItemClickListener(object : NoticeEvent {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(noticeArrayList[position].link))
                startActivity(intent)
            }
        })
        adapter = noticeAdapter
        layoutManager = LinearLayoutManager(requireActivity())
    }
}