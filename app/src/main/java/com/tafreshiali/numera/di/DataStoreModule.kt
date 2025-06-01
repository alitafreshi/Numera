package com.tafreshiali.numera.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import com.tafreshiali.domain.AppProtoDataStore
import com.tafreshiali.domain.model.AppSettings
import com.tafreshiali.repository.AppProtoDataStoreImpl
import com.tafreshiali.serializer.GenericProtoDataStoreSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {

    /** providing app data store for storing values followed by google best practices
     * in this way our app store manage repository does not depend on context
     * @param app
     * for more information see official Docs  =
     * "https://www.youtube.com/watch?v=9ws-cJzlJkU&list=PLWz5rJ2EKKc8to3Ere-ePuco69yBUmQ9C"
     * "https://developer.android.com/topic/libraries/architecture/datastore"
     * "https://developer.android.com/topic/libraries/architecture/datastore?gclid=CjwKCAjwloCSBhAeEiwA3hVo_euHWgRUZszyHhzA_LWk18W5OJVTRweQsoclu3EZ-DF2pgGyVzUyTRoCXuUQAvD_BwE&gclsrc=aw.ds"
     * and an article series about jetpack datastore in medium =
     * "https://medium.com/androiddevelopers/introduction-to-jetpack-datastore-3dc8d74139e7"*/
    /* @Singleton
     @Provides
     fun provideApplicationDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
         PreferenceDataStoreFactory.create(
             corruptionHandler = ReplaceFileCorruptionHandler(
                 produceNewData = { emptyPreferences() }
             ),
             migrations = listOf(
                 SharedPreferencesMigration(
                     context = app,
                     sharedPreferencesName = Constance.APP_DATABASE
                 )
             ),
             produceFile = { app.preferencesDataStoreFile(name = Constance.APP_DATABASE) }
         )*/

    @Singleton
    @Provides
    fun provideAppSettingsDataStoreSerializer(): GenericProtoDataStoreSerializer<AppSettings> =
        GenericProtoDataStoreSerializer(
            serializer = AppSettings.serializer(),
            defaultValue = AppSettings(),
        )

    @Singleton
    @Provides
    fun provideApplicationProtoDataStore(
        @ApplicationContext context: Context,
        serializer: GenericProtoDataStoreSerializer<AppSettings>,
    ): DataStore<AppSettings> = DataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { AppSettings() },
        ),
        serializer = serializer,
        produceFile = { context.dataStoreFile("APP_PROTO_DATASTORE") },
    )

    @Singleton
    @Provides
    fun provideAppProtoDataStore(
        dataStore: DataStore<AppSettings>,
    ): AppProtoDataStore<AppSettings> = AppProtoDataStoreImpl(dataStore = dataStore)
}
