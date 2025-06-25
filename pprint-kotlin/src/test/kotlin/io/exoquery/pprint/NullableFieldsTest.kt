package io.exoquery.pprint

import io.kotest.core.spec.style.FunSpec

data class PersonWithNullable(val name: String, val age: Int?, val email: String?)
data class ComplexNullable(val id: Int, val person: PersonWithNullable?, val tags: List<String>?)

class NullableFieldsTest : FunSpec({
    
    test("nullable fields show field names when null") {
        val person = PersonWithNullable("John", null, "john@example.com")
        Check(fields = true).invoke(person, """PersonWithNullable(name = "John", age = null, email = "john@example.com")""")
    }
    
    test("multiple nullable fields") {
        val person = PersonWithNullable("Jane", 25, null)
        Check(fields = true).invoke(person, """PersonWithNullable(name = "Jane", age = 25, email = null)""")
    }
    
    test("all nullable fields null") {
        val person = PersonWithNullable("Bob", null, null)
        Check(fields = true).invoke(person, """PersonWithNullable(name = "Bob", age = null, email = null)""")
    }
    
    test("nested nullable objects") {
        val complex = ComplexNullable(1, null, listOf("tag1", "tag2"))
        Check(fields = true).invoke(complex, """ComplexNullable(id = 1, person = null, tags = List("tag1", "tag2"))""")
    }
    
    test("nullable fields without field names") {
        val person = PersonWithNullable("Alice", null, "alice@example.com")
        Check(fields = false).invoke(person, """PersonWithNullable("Alice", null, "alice@example.com")""")
    }
})