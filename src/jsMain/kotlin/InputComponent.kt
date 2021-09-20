import kotlinx.html.ButtonType
import react.*
import react.dom.*
import kotlinx.html.js.*
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import org.w3c.dom.HTMLInputElement

external interface InputProps : RProps {
    var onSubmit: (String) -> Unit
}

val InputComponent = functionalComponent<InputProps> { props ->
    val (username, setUsername) = useState("")
    val (password, setPassword) = useState("")

    val submitHandler: (Event) -> Unit = {
        it.preventDefault()
        setUsername("")
        setPassword("")
        props.onSubmit(username)
        props.onSubmit(password)
    }

    val changeHandlerUsername: (Event) -> Unit = {
        val value = (it.target as HTMLInputElement).value
        setUsername(value)
    }

    val changeHandlerPassword: (Event) -> Unit = {
        val value = (it.target as HTMLInputElement).value
        setPassword(value)
    }

    form {
        attrs.onSubmitFunction = submitHandler
        input(InputType.text) {
            attrs.onChangeFunction = changeHandlerUsername
            attrs.value = username
        }
        input(InputType.text) {
            attrs.onChangeFunction = changeHandlerPassword
            attrs.value = password
        }
        button(type = ButtonType.submit) {
            attrs.value = "Log in"
            attrs.onChangeFunction = submitHandler
        }
    }
}