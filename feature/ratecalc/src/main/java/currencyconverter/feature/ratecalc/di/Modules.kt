package currencyconverter.feature.ratecalc.di

import org.koin.core.context.loadKoinModules
import currencyconverter.feature.ratecalc.rateCalculatorModule

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(rateCalculatorModule)
}
