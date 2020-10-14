package com.example.dotacompanion.fragments.encyclopedia.items.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dotacompanion.DotaApplication
import com.example.dotacompanion.data.dotabase.Item
import com.example.dotacompanion.utils.database.DotaDatabase
import com.example.dotacompanion.utils.database.ItemDao


class ItemSharedViewModel(private var database: DotaDatabase?) : ViewModel() {
    private var mainItemId: Int = 0
    private var itemDao: ItemDao? = null
    val itemLiveData = SingleLiveEvent<Item>()

    fun getItem(id: Int) {
        itemLiveData.value = database?.itemDao()?.getItem(id)
    }
}

class MyViewModelFactory(db: DotaDatabase) : ViewModelProvider.Factory {
    private val database: DotaDatabase = db
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ItemSharedViewModel(database) as T
    }
}