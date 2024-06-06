package furhatos.app.socialClient.flow

import furhatos.app.socialClient.flow.main.Idle
import furhatos.app.socialClient.flow.main.Greeting
import furhatos.app.socialClient.setting.activate
import furhatos.app.socialClient.setting.DISTANCE_TO_ENGAGE
import furhatos.app.socialClient.setting.MAX_NUMBER_OF_USERS
import furhatos.app.socialClient.setting.vickiNeural
import furhatos.flow.kotlin.*

val Init: State = state {
    init {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(DISTANCE_TO_ENGAGE, MAX_NUMBER_OF_USERS)

        /** Set character - defined in personas */
        activate(vickiNeural)
    }

    onEntry {
        /** start interaction */
        when {
            furhat.isVirtual() -> goto(Greeting) // Convenient to bypass the need for user when running Virtual Furhat
            users.hasAny() -> {
                furhat.attend(users.random)
                goto(Greeting)
            }
            else -> goto(Idle)
        }
    }

}


