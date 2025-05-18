package com.tafreshiali.repository

import androidx.datastore.core.DataStore
import com.tafreshiali.domain.AppProtoDataStore
import kotlinx.coroutines.flow.Flow

class AppProtoDataStoreImpl<T>(
    private val dataStore: DataStore<T>,
) : AppProtoDataStore<T> {

    override suspend fun setValue(savedObj: T) {
        dataStore.updateData { savedObj }
    }

    override fun getValue(): Flow<T> = dataStore.data
}