import com.target.targetcasestudy.api.DealsRepository
import com.target.targetcasestudy.data.productDetail
import com.target.targetcasestudy.data.productsList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetDealsTest {
    private lateinit var repositoryMock: DealsRepository

    @Before
    fun setup() {
        repositoryMock = mockk()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getDeals() = runTest {
        val deals = productsList
        coEvery { repositoryMock.getDeals() } returns deals
        val res = repositoryMock.getDeals()
        assert(res.isNotEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getDealProductDetail() = runTest {
        val productDetail = productDetail
        coEvery { repositoryMock.getDealInfo("0") } returns productDetail
        val res = repositoryMock.getDealInfo("0")
        assert(res.id == 0)
        assert(res.title == "VIZIO D-Series 40\" Class 1080p Full-Array LED HD Smart TV")
        assert(res.aisle == "b2")
        assert(res.salePrice == null)
        assert(res.fulfillment == "Online")
        assert(res.availability == "In stock")
    }

}