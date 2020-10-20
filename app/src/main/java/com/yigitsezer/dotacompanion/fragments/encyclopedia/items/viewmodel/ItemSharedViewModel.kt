package com.yigitsezer.dotacompanion.fragments.encyclopedia.items.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yigitsezer.dotacompanion.data.dotabase.Item
import com.yigitsezer.dotacompanion.utils.database.DotaDatabase
import com.yigitsezer.dotacompanion.utils.database.ItemDao


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