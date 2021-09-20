import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.*
import org.litote.kmongo.reactivestreams.KMongo
import com.mongodb.ConnectionString

val userList = mutableListOf(
    UserListItem("xGiovanni", "Giovanni", "Mosquera", "nike4545", 27, 7, 2001),
    UserListItem("juseros9", "Sebastián", "Rodriguez", "12345678", 13, 10, 2002),
    UserListItem("juanmiloz", "Juan Camilo", "Zorrilla", "peoncha12", 28, 5, 2002)
)

fun main() {

    embeddedServer(Netty, 9090) {
        install(ContentNegotiation) {
            json()
        }
        install(CORS) {
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Delete)
            anyHost()
        }
        install(Compression) {
            gzip()
        }
        routing {
            route(UserListItem.path) {
                get {
                    call.respond(userList)
                }
                post {
                    userList += call.receive<UserListItem>()
                    call.respond(HttpStatusCode.OK)
                }
                delete("/{username}") {
                    val username = call.parameters["username"]?: error("Invalid delete request")
                    userList.removeIf { it.username == username }
                    call.respond(HttpStatusCode.OK)
                }
            }
            get("/") {
                call.respondText(
                    this::class.java.classLoader.getResource("index.html")!!.readText(),
                    ContentType.Text.Html
                )
            }
            static("/") {
                resources("")
            }
        }
    }.start(wait = true)
}