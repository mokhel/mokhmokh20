package furhatos.app.socialClient.flow.main

import furhatos.app.helper.placeholder
import furhatos.app.socialClient.flow.Parent
import furhatos.app.socialClient.nlu.Stop
import furhatos.app.socialClient.setting.chatgpt.testExample
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures

val Greeting: State = state(Parent) {
    onEntry {
        furhat.ask("Wir testen die Implementierung von ChatGPT, los gehts")
    }

    onResponse<Stop> {
        furhat.say("Ich beende das Gespräch und gebe den Verlauf in der Console aus")
        testExample.toConsole()
        // goto(Idle)
    }

    onResponse {
        furhat.gesture(Gestures.Shake)
        val robotResponse = call {
            testExample.getDialogCompletion()
        } as String?
        placeholder()
        furhat.ask(robotResponse?:"Kannst du das bitte wiederholen")

    }

    onNoResponse { // Catches silence
        furhat.ask("Ich konnte dich nicht verstehen")
    }
}

