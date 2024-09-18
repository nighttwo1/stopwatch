import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Stopwatch {
    private var job: Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    var isRunning by mutableStateOf(false)
        private set

    private var startTime by mutableStateOf(0L)
    private var elapsedTime1 by mutableStateOf(0L)

    // 경과 시간을 반환
    fun getElapsedTime(): Long {
        return if (isRunning) {
            System.currentTimeMillis() - startTime + elapsedTime1
        } else {
            elapsedTime1
        }
    }

    // 타이머 시작
    fun start() {
        if (isRunning) return
        startTime = System.currentTimeMillis()
        isRunning = true
        job = coroutineScope.launch {
            while (isRunning) {
                delay(100L)
            }
        }
    }

    // 타이머 멈춤
    fun stop() {
        if (!isRunning) return
        elapsedTime1 += System.currentTimeMillis() - startTime
        isRunning = false
        job?.cancel()
    }

    // 타이머 리셋
    fun reset() {
        elapsedTime1 = 0L
        startTime = 0L
    }

    fun dispose() {
        job?.cancel()
    }
}
