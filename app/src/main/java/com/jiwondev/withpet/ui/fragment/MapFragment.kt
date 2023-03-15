package com.jiwondev.withpet.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jiwondev.withpet.databinding.FragmentMapBinding
import com.jiwondev.withpet.model.MapDtoItem


const val TAG = "MapFragment"

class MapFragment : BaseFragment<FragmentMapBinding>(FragmentMapBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = Firebase.database
        val myRef = database.getReference("map2")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(object : GenericTypeIndicator<ArrayList<MapDtoItem>>() {})

                // GenericTypeIndicator 클래스는 제네릭 타입에 대한 정보를 보유하는 클래스로,
                // Firebase에서 제공하는 Realtime Database와 같은 NoSQL 데이터베이스에서
                // 제네릭 타입을 사용하여 데이터를 읽거나 쓸 때 유용하게 사용된다.

                value?.forEach {
                    Log.d("result : ", it.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

    }
}