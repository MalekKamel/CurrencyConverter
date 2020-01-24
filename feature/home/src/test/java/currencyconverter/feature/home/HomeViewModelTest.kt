package currencyconverter.feature.home


import org.junit.Test
import org.koin.test.inject


class HomeViewModelTest : BaseUnitTest() {

    private val vm: HomeViewModel by inject()

    @Test
    fun `latestRates() should work correctly`() {
        vm.latestRates()
                .test()
                .assertNoErrors()
    }

}
