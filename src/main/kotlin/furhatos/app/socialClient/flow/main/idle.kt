package furhatos.app.socialClient.flow.main

import furhatos.app.socialClient.flow.main.Greeting
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onUserEnter
import furhatos.flow.kotlin.state

val Idle: State = state {
    onEntry {
        if (furhat.isVirtual())
            goto(Greeting)
        else
            furhat.attendNobody()
    }

    onUserEnter {
        furhat.attend(it)
        goto(Greeting)
    }

}
