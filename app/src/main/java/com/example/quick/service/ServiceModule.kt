package com.example.quick.service


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent



//From your project, JobUseCase is the interface and dagger does not know how to provide him.
// Probably you have to add the binds method for your implementation of the JobUseCase into any dagger module.
//@Binds
//fun bindJobUseCase(interactor: JobInteractor): JobUseCase

@Module
@InstallIn(SingletonComponent ::class)
abstract class ServiceModule {
    @Binds abstract fun provideAccountService(impl: AccountServiceMethods): AccountService

}