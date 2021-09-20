import react.*
import react.dom.*
import kotlinext.js.*
import kotlinx.html.js.*
import kotlinx.coroutines.*

private val scope = MainScope()
var username = ""
var password = ""
var cont = 0
val App = functionalComponent<RProps> { _ ->
    val (shoppingList, setShoppingList) = useState(emptyList<ShoppingListItem>())

    useEffect {
        scope.launch {
            setShoppingList(getShoppingList())
        }
    }

    fun loginAttempt() {
        
    }

    h1 {
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
    }
    child(
        InputComponent,
        props = jsObject {
            onSubmit = { input ->
                if (cont == 0) {
                    username = input
                } else {
                    password = input
                    loginAttempt()
                    cont = 0
                }
                /*val cartItem = ShoppingListItem(input.replace("!", ""), input.count { it == '!' })
                scope.launch {
                    addShoppingListItem(cartItem)
                    setShoppingList(getShoppingList())
                }*/
            }
        }
    )
}