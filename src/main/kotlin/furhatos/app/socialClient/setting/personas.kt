package furhatos.app.socialClient.setting

import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.voice.PollyNeuralVoice
import furhatos.flow.kotlin.voice.Voice
import furhatos.util.Gender
import furhatos.util.Language

class Persona(val name: String, val mask: String = "adult", val face: List<String>, val voice: List<Voice>)

// extend class FlowControlRunner with function activate
fun FlowControlRunner.activate(persona: Persona)
{
    for (voice in persona.voice)
    {
        if (voice.isAvailable)
        {
            furhat.voice = voice
            break
        }
    }

    for (face in persona.face)
    {
        if (furhat.faces[persona.mask]?.contains(face)!!)
        {
            furhat.character = face
            break
        }
    }
}


val vickiNeural = Persona(
    name = "Host",
    face = listOf(("Yumi")),
    voice = listOf(PollyNeuralVoice("Vicki", gender = Gender.FEMALE, language = Language.GERMAN))
)

