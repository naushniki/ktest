package run.smt.ktest.api


interface Boxed {
    operator fun invoke()
}

private class SingleExecution(body: () -> Unit) : Boxed {
    private val wrapped = lazy(body)

    override fun invoke() = wrapped.value
}

private class NormalExecution(private val body: () -> Unit) : Boxed {
    override fun invoke() = body()
}

internal object Noop : Boxed {
    override fun invoke() {}
}

fun List<Interceptor>.execBefore() = forEach { it.before() }
fun List<Interceptor>.execAfter() = reversed().forEach { it.after() }

fun executeOnce(body: () -> Unit): Boxed = SingleExecution(body)
fun executeEveryTime(body: () -> Unit): Boxed = NormalExecution(body)

data class Interceptor(val before: Boxed = Noop, val after: Boxed = Noop)
