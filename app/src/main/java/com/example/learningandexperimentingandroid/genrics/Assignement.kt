package com.example.learningandexperimentingandroid.genrics

/**
 * Create a Repository<out T> interface that allows fetching data but does not allow modifying it.
 * Implement a UserRepository class that fetches User objects.
 */
data class User(val id: Int, val name: String)
interface Repository<out T : User> {
    fun getAllUser(): List<T>
}

class UserRepository() : Repository<User> {
    override fun getAllUser(): List<User> {
         return listOf(
            User(1, "Alice"),
            User(2, "Bob")
        )
    }

}