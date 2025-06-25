package io.exoquery.pprint

import io.exoquery.kmp.pprint
import kotlinx.serialization.Serializable
import kotlin.test.Test

@Serializable
data class PersonWithNullable(val name: String, val age: Int?, val email: String?)

@Serializable
data class ComplexNullable(val id: Int, val person: PersonWithNullable?, val tags: List<String>?)

class NullableFieldsTest {
    
    @Test
    fun testNullableFieldsShowFieldNamesWhenNull() {
        val person = PersonWithNullable("John", null, "john@example.com")
        Check(fields = true).invoke(person, """PersonWithNullable(name = "John", age = null, email = "john@example.com")""")
    }
    
    @Test
    fun testMultipleNullableFields() {
        val person = PersonWithNullable("Jane", 25, null)
        Check(fields = true).invoke(person, """PersonWithNullable(name = "Jane", age = 25, email = null)""")
    }
    
    @Test
    fun testAllNullableFieldsNull() {
        val person = PersonWithNullable("Bob", null, null)
        Check(fields = true).invoke(person, """PersonWithNullable(name = "Bob", age = null, email = null)""")
    }
    
    @Test
    fun testNestedNullableObjects() {
        val complex = ComplexNullable(1, null, listOf("tag1", "tag2"))
        Check(fields = true).invoke(complex, """ComplexNullable(id = 1, person = null, tags = List("tag1", "tag2"))""")
    }
    
    @Test
    fun testNullableFieldsWithoutFieldNames() {
        val person = PersonWithNullable("Alice", null, "alice@example.com")
        Check(fields = false).invoke(person, """PersonWithNullable("Alice", null, "alice@example.com")""")
    }
}