package com.jiwondev.withpet.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.search.SearchView.Behavior
import com.jiwondev.withpet.R
import com.jiwondev.withpet.data.datasourece.MapDatasource
import com.jiwondev.withpet.data.repository.MapRepository
import com.jiwondev.withpet.databinding.BottomSheetDialogBinding
import com.jiwondev.withpet.databinding.FragmentMapBinding
import com.jiwondev.withpet.model.MapDtoItem
import com.jiwondev.withpet.ui.activity.StoreDetailActivity
import com.jiwondev.withpet.ui.viewmodel.MapViewModel
import com.jiwondev.withpet.ui.viewmodel.MapViewModelFactory
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.MapFragment
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay.OnClickListener
import com.naver.maps.map.util.MarkerIcons
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MapFragment : BaseFragment<FragmentMapBinding>(FragmentMapBinding::inflate), OnMapReadyCallback {
    lateinit var mapViewModel: MapViewModel
    private lateinit var storeInfoBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onMapReady(naverMap: NaverMap) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mapViewModel.mapFlow.collectLatest { mapResponse ->
                        mapResponse?.forEach { mapInfo ->
                            Marker().apply {
                                position = LatLng(mapInfo.latitude, mapInfo.longitude)
                                map = naverMap
                                icon = MarkerIcons.BLACK
                                iconTintColor = Color.parseColor("#FFBA00")
                                width = 80
                                height = 130
                                captionText = mapInfo.storeName
                                captionColor = Color.parseColor("#FFBA00")
                                onClickListener = OnClickListener {
                                    storeInfoBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                                    showMarkerInfoBottomSheetDialog(mapInfo)
                                    true
                                }
                            }
                        }
                    }
                }
            }
        }
        naverMap.addOnCameraChangeListener { reason, _ ->
            Log.d("BottomSheet : ", storeInfoBehavior.state.toString())
        }
//
//        /** The bottom sheet is dragging. */
//        public static final int STATE_DRAGGING = 1;
//
//        /** The bottom sheet is settling. */
//        public static final int STATE_SETTLING = 2;
//
//        /** The bottom sheet is expanded. */
//        public static final int STATE_EXPANDED = 3;
//
//        /** The bottom sheet is collapsed. */
//        public static final int STATE_COLLAPSED = 4;
//
//        /** The bottom sheet is hidden. */
//        public static final int STATE_HIDDEN = 5;
//
//        /** The bottom sheet is half-expanded (used when fitToContents is false). */
//        public static final int STATE_HALF_EXPANDED = 6;
    }

    private fun init() {
        // TODO : 현재위치  파악.
        mapViewModel = ViewModelProvider(
            this,
            MapViewModelFactory(MapRepository(MapDatasource()))
        )[MapViewModel::class.java]

        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.naverMap) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.naverMap, it).commit()
            }
        mapFragment.getMapAsync(this)

        storeInfoBehavior = BottomSheetBehavior.from(binding.markerInfoBottomSheet.markerBottomSheetConstraint)
        storeInfoBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }


    private fun showMarkerInfoBottomSheetDialog(mapInfo: MapDtoItem) {
        binding.markerInfoBottomSheet.apply {
            storeNameTextView.text = "${mapInfo.storeName} - ${mapInfo.typeDetail}"
            loadAddressTextView.text = mapInfo.loadAddress
            dayOffTextView.text = mapInfo.dayOff
            bottomSheetCloseImageView.setOnClickListener { storeInfoBehavior.state = BottomSheetBehavior.STATE_HIDDEN }
            markerBottomSheetConstraint.setOnClickListener {
                val intent = Intent(requireContext(), StoreDetailActivity::class.java)
                intent.putExtra("data", mapInfo)
                requireActivity().startActivity(intent)
            }
        }
    }
}