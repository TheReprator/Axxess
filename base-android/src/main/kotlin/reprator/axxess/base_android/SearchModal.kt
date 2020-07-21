package reprator.axxess.base_android

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchModal(
 @JvmField
 val id: String,
 val title: String,
 val imageUrl: String
): Parcelable