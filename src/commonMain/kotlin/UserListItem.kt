import kotlinx.serialization.Serializable

@Serializable
data class UserListItem(val username: String, val firstname: String, val lastname: String, val password: String, val day: Int, val month: Int, val year: Int) {
    companion object {
        const val path = "/users"
    }
}