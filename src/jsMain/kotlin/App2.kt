import react.*
import react.dom.*
import kotlinext.js.*
import kotlinx.browser.document
import kotlinx.html.js.*
import kotlinx.coroutines.*
import kotlinx.html.onClick
import org.w3c.dom.events.Event

private val scope = MainScope()

val App2 = functionalComponent<RProps> { _ ->

    val (userList, setUserList) = useState(emptyList<UserListItem>())

    useEffect {
        scope.launch {
            setUserList(getUserList())
        }
    }

    fun logout(): (Event) -> Unit = {
        println("You have logout")
    }

    div(classes = "mx-auto col-md-5") {
        div(classes = "d-block text-center") {
            div(classes = "mt-3") {
                table(classes = "table mt-4") {
                    thead(classes = "thead-dark") {
                        tr {
                            th {
                                +"Username"
                            }
                            th {
                                +"Firstname"
                            }
                            th {
                                +"Lastname"
                            }
                            th {
                                +"Birthdate"
                            }
                        }
                    }
                    tbody {
                        for(user in userList) {
                            tr {
                                td {
                                    +user.username
                                }
                                td {
                                    +user.firstname
                                }
                                td {
                                    +user.lastname
                                }
                                td {
                                    +"${user.day} / ${user.month} / ${user.year}"
                                }
                            }
                        }
                    }
                }
                a(href = "/") {
                    button {
                        +"Log out"
                        attrs.onClickFunction = logout()
                    }
                }
            }
        }
    }
}
