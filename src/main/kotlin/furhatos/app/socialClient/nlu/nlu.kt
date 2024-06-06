package furhatos.app.socialClient.nlu

import furhatos.nlu.Intent
import furhatos.util.Language


// Inherit from class Intent() and define own intents
class Stop: Intent()
{
    override fun getExamples(lang: Language): List<String>
    {
        return listOf("Stop", "Stopp")
    }
}
