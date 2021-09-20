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

    form() {
        attrs.onSubmitFunction = submitHandler
        div(classes = "form-group") {
            label(classes = "control-label") {
                +"Username"
            }
            input(InputType.text, classes = "form-control") {
                attrs.onChangeFunction = changeHandlerUsername
                attrs.value = username
            }
        }
        div(classes = "form-group") {
            label(classes = "control-label") {
                +"Password"
            }
            input(InputType.text, classes = "form-control") {
                attrs.onChangeFunction = changeHandlerPassword
                attrs.value = password
            }
        }
        div(classes = "form-group mt-3") {
            button(type = ButtonType.submit, classes = "btn btn-primary btn-block") {
                +"Log in"
                attrs.onChangeFunction = submitHandler
            }
        }
        hr(classes = "mt-3") { }
        div(classes = "form-group") {
            button(classes = "btn btn-outline-primary btn-block") {
                +"Register"
                //attrs.onChangeFunction = submitHandler
            }
        }
    }
}