import io.ktor.http.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

import kotlinx.browser.window

val endpoint = window.location.origin // only needed until https://youtrack.jetbrains.com/issue/KTOR-453 is resolved

val jsonClient = HttpClient {
    install(JsonFeature) { serializer = KotlinxSerializer() }
}

suspend fun getUserList(): List<UserListItem> {
    return jsonClient.get(endpoint + UserListItem.path)
}

suspend fun addUserListItem(userListItem: UserListItem) {
    jsonClient.post<Unit>(endpoint + UserListItem.path) {
        contentType(ContentType.Application.Json)
        body = userListItem
    }
}

suspend fun deleteUserListItem(userListItem: UserListItem) {
    jsonClient.delete<Unit>(endpoint + UserListItem.path + "/${userListItem.username}")
}