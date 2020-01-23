package currencyconverter.feature.home.di

import org.koin.core.context.loadKoinModules
import currencyconverter.feature.home.homeModule

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(homeModule)
}
