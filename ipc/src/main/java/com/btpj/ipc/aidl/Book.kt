package com.btpj.ipc.aidl

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author LTP  2021/7/29
 */
@Parcelize
data class Book(var id: Int, var name: String) : Parcelable
