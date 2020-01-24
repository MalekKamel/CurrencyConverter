package currencyconverter.common.presentation


import androidx.annotation.NonNull
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.ClassRule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.test.AutoCloseKoinTest
import java.util.concurrent.TimeUnit

open class BaseUnitTest: AutoCloseKoinTest() {
    companion object {
//        @JvmStatic
//        @get:ClassRule
//        val schedulers = RxImmediateSchedulerRule()

        @JvmStatic
        @get:ClassRule
        val instantTaskExecutorRule = InstantTaskExecutorRule()
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