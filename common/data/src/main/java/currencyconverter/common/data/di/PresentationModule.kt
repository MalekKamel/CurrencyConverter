package currencyconverter.common.data.di

import android.preference.PreferenceManager
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import currencyconverter.common.data.BuildConfig
import currencyconverter.common.data.DataManager
import currencyconverter.common.data.currencyrate.CurrencyRateApi
import currencyconverter.common.data.network.interceptor.TokenInterceptor
import currencyconverter.common.data.pref.SharedPref
import currencyconverter.common.data.currencyrate.CurrencyRateDataSrc
import currencyconverter.common.data.currencyrate.CurrencyRatesRepo
import currencyconverter.common.data.rx.SchedulerProvider
import currencyconverter.common.data.rx.SchedulerProviderImpl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun injectDataModule() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(dataModule)
}

val dataModule = module {
    factory { CurrencyRatesRepo(get()) }
    factory { CurrencyRateDataSrc(get()) }

    single { DataManager(get(), get()) }
    single { TokenInterceptor(get()) }
    single { SharedPref(get()) }
    single<SchedulerProvider> { SchedulerProviderImpl() }

    // default pref
    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }

    // OkHttpClient
    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val builder = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(get<TokenInterceptor>())
        builder.build()
    }

    // ApiInterface
    single {

        Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
                .create(CurrencyRateApi::class.java)
    }

}
