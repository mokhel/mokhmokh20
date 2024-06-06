package furhatos.app.socialClient.setting.chatgpt

import com.theokanning.openai.completion.chat.ChatCompletionRequest
import com.theokanning.openai.completion.chat.ChatMessage
import com.theokanning.openai.service.OpenAiService
import furhatos.flow.kotlin.DialogHistory
import furhatos.flow.kotlin.Furhat
import java.io.File
import java.nio.file.Paths


fun getEmotion(answer: String): String
{
    val emotion = answer.substring(answer.indexOf("[") + 1, answer.indexOf("]"))
    return answer.replace("[$emotion]", "")
}

class ChatGPT(
    private val apiKey: OpenAiService,
    private var systemPrompt: String =""
)
{
    private var history = ""

    fun getDialogCompletion(contextWindowSize: Int = 10): String?
    {
        this.systemPrompt = loadPrompt()
        val messages = mutableListOf(ChatMessage().apply { role = "system"; content = systemPrompt})


        Furhat.dialogHistory.all.takeLast(contextWindowSize).forEach {
            when (it)
            {
                is DialogHistory.ResponseItem ->
                    { messages.add(ChatMessage().apply { role = "user"; content = it.response.text }) }
                is DialogHistory.UtteranceItem ->
                    { messages.add(ChatMessage().apply { role = "assistant"; content = it.toText() }) }
                else -> null
            }
        }

        this.history += messages

        val prompt = "$systemPrompt\n\n$history\nChatGPT:"
        val completionRequest = ChatCompletionRequest.builder()
            .messages(messages)
            .model("gpt-3.5-turbo")
            .build()

        try
        {
            val completion = apiKey.createChatCompletion(completionRequest).choices.first().message.content
            println(getEmotion(completion.trim()))
            return getEmotion(completion.trim())
        } catch (e: Exception)
        {
            println("problem with connection to OpenAI\n ERROR: ${e.localizedMessage}")
        }

        return null
    }

    fun loadPrompt(): String
    {
        val cwd = Paths.get("").toAbsolutePath().toString()
        val rPath =  "/src/main/kotlin/furhatos/app/socialClient/setting/chatgpt/prompt.txt"
        val aPath = cwd + rPath

        return if (File(aPath).exists()) {
            File(aPath).absoluteFile.inputStream().readBytes().toString(Charsets.UTF_8)
        } else
            "File not found! \n $aPath"
    }

    fun toConsole()
    {
        println(this.history)
    }
}

val testExample = ChatGPT(
    

