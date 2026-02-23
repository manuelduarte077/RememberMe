package dev.donmanuel.rememberme.di

import dev.donmanuel.rememberme.data.network.createPlatformHttpClient
import dev.donmanuel.rememberme.data.remote.RememberMeRemoteDataSource
import dev.donmanuel.rememberme.data.repository.BirthdayRepositoryImpl
import dev.donmanuel.rememberme.domain.repository.BirthdayRepository
import dev.donmanuel.rememberme.domain.usecase.GetBirthdaysUseCase
import dev.donmanuel.rememberme.presentation.viewmodel.BirthdaysViewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.core.Koin
import org.koin.dsl.module

private val appModule: Module = module {
    single { createPlatformHttpClient() }
    single { RememberMeRemoteDataSource(get()) }
    single<BirthdayRepository> { BirthdayRepositoryImpl(get()) }
    factory { GetBirthdaysUseCase(get()) }
    single { BirthdaysViewModel(get()) }
}

object AppDi {
    private var started = false
    private lateinit var koin: Koin

    fun start() {
        if (started) {
            return
        }

        val koinApplication = startKoin {
            modules(appModule)
        }
        koin = koinApplication.koin
        started = true
    }

    fun birthdaysViewModel(): BirthdaysViewModel {
        start()
        return koin.get()
    }

    fun stop() {
        if (!started) {
            return
        }
        stopKoin()
        started = false
    }
}
