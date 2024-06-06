package furhatos.app.helper

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import furhatos.app.socialClient.SocialClientSkill
import furhatos.flow.kotlin.DialogHistory
import furhatos.flow.kotlin.Furhat
import furhatos.skills.Skill
import java.io.File
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



fun getDate(): String
{
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val currentDate: String = LocalDateTime.now().format(formatter)
    return currentDate
}

fun placeholder()
{
    val history = Furhat.dialogHistory.all.takeLast(2).mapNotNull {
        when(it)
        {
            is DialogHistory.ResponseItem ->
            { getDate() + " - User:" + it.response.text }
            is DialogHistory.UtteranceItem ->
            { getDate() + " - ChatGPT:" + it.toText() }
            else -> null
        }
    }.joinToString(separator = "\n")

    File(getDate()).appendText(history + "\n")
}


class Placeholder
{




}