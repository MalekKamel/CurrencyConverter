package currencyconverter.feature.ratecalc

import org.junit.Test
import org.koin.test.inject

class RateCalculatorViewModelTest: BaseUnitTest() {

    private val vm: RateCalculatorViewModel by inject()

    @Test
    fun `calculateRate() should return correct value`() {
        val amount = 2.0
        val base = 2.0
        val rate = vm.calculateRate(amount, base)
        assert(rate == 4.0)
    }

}