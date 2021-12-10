package me.andrewreed

import app.cash.turbine.test
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import kotlin.test.*

class ViewModelTests {

    @BeforeTest
    fun before() {
        startKoin {
            printLogger(Level.ERROR)
            modules(
                module {
                    single { ProcessingStuff() }
                }
            )
        }
    }

    @AfterTest
    fun after() {
        stopKoin()
    }

    @Test
    fun `Empty results should change state to empty`() = runBlocking {
        val viewModel = BrokenViewModel()
        viewModel.state.test {
            assertNull(awaitItem())
            assertNotNull(awaitItem())
        }
    }

}