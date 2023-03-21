package com.jiwondev.withpet.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jiwondev.withpet.databinding.NoticeItemBinding
import com.jiwondev.withpet.model.NoticeDto
import com.jiwondev.withpet.ui.adapter.event.NoticeEvent

class NoticeAdapter(private val noticeArrayList: ArrayList<NoticeDto>): RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {
    lateinit var clickListener: NoticeEvent

    fun setOnItemClickListener(listener : NoticeEvent) {
        this.clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val noticeViewHolder = NoticeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(noticeViewHolder)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(noticeArrayList[position])
        
        holder.itemView.setOnClickListener {
            clickListener.onClick(it, position)
        }
    }

    override fun getItemCount() = noticeArrayList.size

    inner class NoticeViewHolder(private val binding: NoticeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoticeDto) {
            binding.apply {
                noticeTitleTextView.text = item.title
                noticeContentTextView.text = item.content
                noticeRegisterDayTextView.text = item.registerDay
            }
        }
    }
}