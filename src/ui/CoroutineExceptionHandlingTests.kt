package ui

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertTrue

@Suppress("FunctionName")
class CoroutineExceptionHandlingTests {

    class FakePresenterForSingleExceptionHandling(val onSecondAction: () -> Unit) : BasePresenter() {

        var cancelledJobs = 0

        fun onCreate() {
            // TODO Uncomment
//            launch {
//                delay(100)
//                throw Error()
//            }
//            launch {
//                delay(200)
//                onSecondAction()
//            }
        }
    }

    @Test
    fun `Error on a single coroutine, does not cancel others`() = runBlocking {
        var called = false
        val presenter = FakePresenterForSingleExceptionHandling(
                onSecondAction = { called = true }
        )
        presenter.onCreate()
        delay(300)
        assertTrue(called)
    }
}