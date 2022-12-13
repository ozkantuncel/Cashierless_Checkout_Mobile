package com.ozkan.cashierlesscheckout.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.ozkan.cashierlesscheckout.firebase.remote.OrderRepository
import com.ozkan.cashierlesscheckout.firebase.repository.OrderRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideOrderRepository(
        database: FirebaseFirestore,
        gson: Gson
    ): OrderRepository {
        return OrderRepositoryImpl(
            database = database,
            gson = gson
        )
    }
}