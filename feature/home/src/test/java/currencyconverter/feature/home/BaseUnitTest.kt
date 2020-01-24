package currencyconverter.feature.home


import android.content.Context
import androidx.annotation.NonNull
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import currencyconverter.common.data.di.presentationModule
import currencyconverter.common.data.pref.SharedPref
import currencyconverter.common.data.restaurant.CurrencyRatesRepo
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.ClassRule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.test.AutoCloseKoinTest
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import java.util.concurrent.TimeUnit

open class BaseUnitTest: AutoCloseKoinTest() {
    companion object {
        @JvmStatic
        @get:ClassRule
        val schedulers = RxImmediateSchedulerRule()

        @JvmStatic
        @get:ClassRule
        val instantTaskExecutorRule = InstantTaskExecutorRule()
    }

    @Before
    fun before() {
        startKoin {
            androidContext(Mockito.mock(Context::class.java))
            modules(listOf(homeModule, presentationModule))
            declareMock<SharedPref> {}
            declareMock<CurrencyRatesRepo> {}
            printLogger(Level.DEBUG)
        }
    }

}

class RxImmediateSchedulerRule: TestRule {
    private val immediate = object: Scheduler() {
        override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
            // this prevents StackOverflowErrors when scheduling with a delay
            return super.scheduleDirect(run, 0, unit);
        }

        override fun createWorker() = ExecutorScheduler.ExecutorWorker({}, true)
    }

    override fun apply(base: Statement?, description: Description?): Statement {
        return object: Statement() {
            override fun evaluate() {
                RxJavaPlugins.setInitIoSchedulerHandler { immediate }
                RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
                RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
                RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }

                try {
                    base?.evaluate();
                } finally {
                    RxJavaPlugins.reset();
                    RxAndroidPlugins.reset();
                }
            }
        }
    }

}