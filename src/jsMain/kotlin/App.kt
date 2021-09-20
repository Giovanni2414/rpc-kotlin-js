import react.*
import react.dom.*
import kotlinext.js.*
import kotlinx.browser.document
import kotlinx.html.js.*
import kotlinx.coroutines.*

private val scope = MainScope()

var username = ""
var password = ""
var cont = 0
var isLogged = false

val App = functionalComponent<RProps> { _ ->
    val (userList, setUserList) = useState(emptyList<UserListItem>())

    useEffect {
        scope.launch {
            setUserList(getUserList())
            isLogged = false
        }
    }

    fun loginAttempt() {
        println("Process the login attempt " + username + " " + password)
        for (user in userList) {
            if(user.username == username) {
                if(user.password == password) {
                    print("You have log in!")
                    isLogged = true
                }
            }
        }
    }

    fun renderLoginView() {
        render(document.getElementById("root")) {
            child(App2)
        }
    }

    /*h1 {
        +"Full-Stack Shopping List"
    }
    ul {
        shoppingList.sortedByDescending(ShoppingListItem::priority).forEach { item ->
            li {
                key = item.toString()
                +"[${item.priority}] ${item.desc} "
            }
            attrs.onClickFunction = {
                scope.launch {
                    deleteShoppingListItem(item)
                    setShoppingList(getShoppingList())
                }
            }
        }
    }*/
    child(
        InputComponent,
        props = jsObject {
            onSubmit = { input ->
                if (cont == 0) {
                    username = input
                    cont++
                    println("Primer cont")
                } else {
                    password = input
                    print("Voy al contador")
                    loginAttempt()
                    cont = 0
                    if(isLogged) {
                        renderLoginView()
                    }
                }
                println(cont)
                /*val cartItem = ShoppingListItem(input.replace("!", ""), input.count { it == '!' })
                scope.launch {
                    addShoppingListItem(cartItem)
                    setShoppingList(getShoppingList())
                }*/
            }
        }
    )
}