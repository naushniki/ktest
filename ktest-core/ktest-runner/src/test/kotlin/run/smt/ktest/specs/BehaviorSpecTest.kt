package run.smt.ktest.specs

import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.should.shouldMatch
import run.smt.ktest.ListStack

class BehaviorSpecTest : BehaviorSpec({
    given("a ListStack") {
        `when`("pop is invoked") {
            then("the last element is removed") {
                val stack = ListStack<String>()
                stack.push("hello")
                stack.push("world")
                stack.size() shouldMatch equalTo(2)
                stack.pop() shouldMatch equalTo("world")
                stack.size() shouldMatch equalTo(1)
            }
        }
    }
})
