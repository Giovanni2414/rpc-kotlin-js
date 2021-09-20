import react.*
import react.dom.*
import kotlinext.js.*
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.js.*
import kotlinx.coroutines.*
import org.w3c.fetch.Response.Companion.redirect

private val scope = MainScope()

var contR = 0
var usernameR = ""
var passwordR = ""
var confirmPassword = ""
var firstname = ""
var lastname = ""
var day = 0
var month = 0
var year = 0

val App3 = functionalComponent<RProps> { _ ->
    val (userList, setUserList) = useState(emptyList<UserListItem>())

    useEffect {
        scope.launch {
            setUserList(getUserList())
            isLogged = false
        }
    }

    fun registerAttempt() {
        if(usernameR.isNotEmpty() && passwordR.isNotEmpty() && confirmPassword.isNotEmpty() && firstname.isNotEmpty() && lastname.isNotEmpty() && day != 0 && month != 0 && year != 0) {
            if(passwordR == confirmPassword) {
                val user = UserListItem(usernameR, firstname, lastname, passwordR, day, month, year)
                scope.launch {
                    println("User created")
                    addUserListItem(user)
                    setUserList(getUserList())
                    window.location.reload()
                //redirect("/", 302)
                }
            } else {
                render(document.getElementById("messages")) {
                    div(classes = "alert alert-warning mt-3") {
                        +"The passwords doesn't match"
                    }
                }
            }
        } else {
            render(document.getElementById("messages")) {
                div(classes = "alert alert-warning mt-3") {
                    +"Do not leave empty fields"
                }
            }
        }
    }

    child(
        InputComponentRegister,
        props = jsObject {
            onSubmit = { input ->
                if(cont == 0) {
                    usernameR = input
                    cont++
                } else if(cont == 1) {
                    passwordR = input
                    cont++
                } else if(cont == 2) {
                    confirmPassword = input
                    cont++
                } else if(cont == 3) {
                    firstname = input
                    cont++
                } else if(cont == 4) {
                    lastname = input
                    cont++
                } else if(cont == 5) {
                    day = input.toInt()
                    cont++
                } else if(cont == 6) {
                    month = input.toInt()
                    cont++
                } else if(cont == 7) {
                    year = input.toInt()
                    cont = 0
                    registerAttempt()
                }
            }
        }
    )
}
