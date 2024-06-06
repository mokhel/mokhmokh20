package furhatos.app.socialClient

import furhatos.app.socialClient.flow.Init
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill

class SocialClientSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
