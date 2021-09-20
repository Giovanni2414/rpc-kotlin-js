import kotlinx.html.ButtonType
import react.*
import react.dom.*
import kotlinx.html.js.*
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import org.w3c.dom.HTMLInputElement

val InputComponentRegister = functionalComponent<InputProps> { props ->
    val (username, setUsername) = useState("")
    val (password, setPassword) = useState("")
    val (firstname, setFirstname) = useState("")
    val (lastname, setLastname) = useState("")
    val (day, setDay) = useState(0)
    val (month, setMonth) = useState(0)
    val (year, setYear) = useState(0)
    val (confirmPassword, setConfirmPassword) = useState("");

    val submitHandlerRegister: (Event) -> Unit = {
        it.preventDefault()
        setUsername("")
        setPassword("")
        setFirstname("")
        setLastname("")
        setDay(0)
        setMonth(0)
        setYear(0)
        setConfirmPassword("")

        props.onSubmit(username)
        props.onSubmit(password)
        props.onSubmit(confirmPassword)
        props.onSubmit(firstname)
        props.onSubmit(lastname)
        props.onSubmit(day.toString())
        props.onSubmit(month.toString())
        props.onSubmit(year.toString())
    }

    val changeHandlerUsernameRegister: (Event) -> Unit = {
        val value = (it.target as HTMLInputElement).value
        setUsername(value)
    }

    val changeHandlerPassword: (Event) -> Unit = {
        val value = (it.target as HTMLInputElement).value
        setPassword(value)
    }

    val changeHandlerFirstname: (Event) -> Unit = {
        val value = (it.target as HTMLInputElement).value
        setFirstname(value)
    }

    val changeHandlerLastname: (Event) -> Unit = {
        val value = (it.target as HTMLInputElement).value
        setLastname(value)
    }

    val changeHandlerConfirmPassword: (Event) -> Unit = {
        val value = (it.target as HTMLInputElement).value
        setConfirmPassword(value)
    }

    val changeHandlerDay: (Event) -> Unit = {
        val value = ((it.target as HTMLInputElement).value).toInt()
        setDay(value)
    }

    val changeHandlerMonth: (Event) -> Unit = {
        val value = ((it.target as HTMLInputElement).value).toInt()
        setMonth(value)
    }

    val changeHandlerYear: (Event) -> Unit = {
        val value = ((it.target as HTMLInputElement).value).toInt()
        setYear(value)
    }

    form() {
        attrs.onSubmitFunction = submitHandlerRegister
        div(classes = "form-group") {
            label(classes = "control-label") {
                +"Username"
            }
            input(InputType.text, classes = "form-control") {
                attrs.onChangeFunction = changeHandlerUsernameRegister
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
        div(classes = "form-group") {
            label(classes = "control-label") {
                +"Confirm Password"
            }
            input(InputType.text, classes = "form-control") {
                attrs.onChangeFunction = changeHandlerConfirmPassword
                attrs.value = confirmPassword
            }
        }
        div(classes = "form-group") {
            label(classes = "control-label") {
                +"Firstname"
            }
            input(InputType.text, classes = "form-control") {
                attrs.onChangeFunction = changeHandlerFirstname
                attrs.value = firstname
            }
        }
        div(classes = "form-group") {
            label(classes = "control-label") {
                +"Lastname"
            }
            input(InputType.text, classes = "form-control") {
                attrs.onChangeFunction = changeHandlerLastname
                attrs.value = lastname
            }
        }
        div(classes = "form-group") {
            label(classes = "control-label") {
                +"Your birthday <br> Day"
            }
            input(InputType.text, classes = "form-control") {
                attrs.onChangeFunction = changeHandlerDay
                attrs.value = day.toString()
            }
        }
        div(classes = "form-group") {
            label(classes = "control-label") {
                +"Month"
            }
            input(InputType.text, classes = "form-control") {
                attrs.onChangeFunction = changeHandlerMonth
                attrs.value = month.toString()
            }
        }
        div(classes = "form-group") {
            label(classes = "control-label") {
                +"Year"
            }
            input(InputType.text, classes = "form-control") {
                attrs.onChangeFunction = changeHandlerYear
                attrs.value = year.toString()
            }
        }
        hr(classes = "mt-3") { }
        div(classes = "form-group mt-3") {
            button(type = ButtonType.submit, classes = "btn btn-primary btn-block") {
                +"Register"
                attrs.onChangeFunction = submitHandlerRegister
            }
        }
    }
}
