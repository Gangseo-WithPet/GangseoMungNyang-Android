package com.jiwondev.withpet.ui.activity

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import com.jiwondev.withpet.databinding.ActivityStoreDetailBinding
import com.jiwondev.withpet.model.MapDtoItem

class StoreDetailActivity : BaseActivity<ActivityStoreDetailBinding>({ ActivityStoreDetailBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        getSerializableData()?.let {
//            bindingData(it)
//        } ?: run {
//            Toast.makeText(this@StoreDetailActivity, "상세 데이터가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
//            finish()
//        }
    }

    private fun getSerializableData(): MapDtoItem? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("data", MapDtoItem::class.java)
        } else {
            intent.getSerializableExtra("data") as? MapDtoItem
        }
    }

//    private fun bindingData(mapDtoItem: MapDtoItem) {
//        binding.apply {
//            storeCloseImageView.setOnClickListener { finish() }
//            storeNameTextView.text = mapDtoItem.storeName
//            storeTypeTextView.text = "${mapDtoItem.type} - ${mapDtoItem.typeDetail}"
//            storeLoadAddressTextView.text = mapDtoItem.loadAddress
//            storeAddressTextView.text = mapDtoItem.address
//            storeNumberTextView.text = mapDtoItem.number
//            storeParkingTextView.text = mapDtoItem.parking
//            storeTimeTextView.text = timeSplitResult(mapDtoItem.time)
//            storeChargeTextView.text = mapDtoItem.charge
//            storeDayoffTextView.text = mapDtoItem.dayOff
//            storeAdmissionTextView.text = mapDtoItem.admission
//            storeAccompanyTextView.text = "${mapDtoItem.accompany} - ${mapDtoItem.accompanySize}"
//            storePedestriansTextView.text = mapDtoItem.pedestrians
//            storeLimitedTextView.text = mapDtoItem.limitation
//            storeInsideTextView.text = mapDtoItem.inside
//            storeOutsideTextView.text = mapDtoItem.outside
//        }
//    }

    private fun timeSplitResult(time: String): String {
        var timeList = ""
        time.split(", ").forEach {
            timeList += "${it}\n"
        }
        return timeList
    }
}
