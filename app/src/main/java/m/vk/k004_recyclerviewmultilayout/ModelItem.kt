package m.vk.k004_recyclerviewmultilayout

import android.os.Parcel
import android.os.Parcelable

open class ModelItem : Parcelable {
    var title: String
    var position: String
    var viewType: Int = 0

    constructor(pTitle: String,pPosition: String,pViewType: Int){
        title = pTitle
        position = pPosition
        viewType = pViewType
    }

    constructor(parcel: Parcel){
        title = parcel.readString()
        position = parcel.readString()
        viewType = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        with(parcel){
            writeString(title)
            writeString(position)
            writeInt(viewType)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelItem> {
        override fun createFromParcel(parcel: Parcel): ModelItem {
            return ModelItem(parcel)
        }

        override fun newArray(size: Int): Array<ModelItem?> {
            return arrayOfNulls(size)
        }
    }
}