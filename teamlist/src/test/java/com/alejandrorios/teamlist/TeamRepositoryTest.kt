package com.alejandrorios.teamlist

import com.alejandrorios.teamlist.data.entities.APITeamData
import com.alejandrorios.teamlist.data.mapper.APITeamMapper
import com.alejandrorios.teamlist.data.repository.TeamRepositoryImpl
import com.alejandrorios.teamlist.data.service.GetTeamService
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

/**
 * Created by Alejandro Rios on 2019-10-24
 */
class TeamRepositoryTest : MockableTest {

    @MockK
    lateinit var getTeamService: GetTeamService

    @Before
    override fun setup() {
        super.setup()

        coEvery {
            getTeamService.getTeams("4328")
        }.answers {
            listOf(
                APITeamData(
                    "133604",
                    "Arsenal",
                    "Gunners",
                    "1892",
                    "Emirates Stadium",
                    "www.arsenal.com",
                    "www.facebook.com/Arsenal",
                    "twitter.com/arsenal",
                    "instagram.com/arsenal",
                    "Arsenal Football Club is a professional football club based in Holloway, London which currently plays in the Premier League, the highest level of English football. One of the most successful clubs in English football, they have won 13 First Division and Premier League titles and a joint record 11 FA Cups.\r\n\r\nArsenal's success has been particularly consistent: the club has accumulated the second most points in English top-flight football, hold the ongoing record for the longest uninterrupted period in the top flight, and would be placed first in an aggregated league of the entire 20th century. Arsenal is the second side to complete an English top-flight season unbeaten (in the 2003–04 season), playing almost twice as many matches as the previous invincibles Preston North End in the 1888–89 season.\r\n\r\nArsenal was founded in 1886 in Woolwich and in 1893 became the first club from the south of England to join the Football League. In 1913, they moved north across the city to Arsenal Stadium in Highbury. In the 1930s, they won five League Championship titles and two FA Cups. After a lean period in the post-war years they won the League and FA Cup Double, in the 1970–71 season, and in the 1990s and first decade of the 21st century, won two more Doubles and reached the 2006 UEFA Champions League Final. Since neighbouring Tottenham Hotspur, the two clubs have had a fierce rivalry, the North London derby.\r\n\r\nArsenal have one of the highest incomes and largest fanbases in the world. The club was named the fifth most valuable association football club in the world, valued at £1.3 billion in 2014.",
                    "https://www.thesportsdb.com/images/media/team/badge/a1af2i1557005128.png",
                    "https://www.thesportsdb.com/images/media/team/jersey/2019-133604-Jersey.png",
                    "www.youtube.com/user/ArsenalTour"
                )
            )
        }
    }

    @Test
    fun `should get teams`() {
        val repository = given {
            TeamRepositoryImpl(getTeamService, APITeamMapper)
        }

        val result = whenever {
            runBlocking {
                repository.getTeamsList("4328")
            }
        }

        then {
            result[0].idTeam shouldEqual "133604"
            result[0].strTeam shouldEqual "Arsenal"
            result[0].strAlternate shouldEqual "Gunners"
            result[0].intFormedYear shouldEqual "1892"
            result[0].strStadium shouldEqual "Emirates Stadium"
            result[0].strWebsite shouldEqual "www.arsenal.com"
            result[0].strFacebook shouldEqual "www.facebook.com/Arsenal"
            result[0].strTwitter shouldEqual "twitter.com/arsenal"
            result[0].strInstagram shouldEqual "instagram.com/arsenal"
            result[0].strDescriptionEN shouldEqual "Arsenal Football Club is a professional football club based in Holloway, London which currently plays in the Premier League, the highest level of English football. One of the most successful clubs in English football, they have won 13 First Division and Premier League titles and a joint record 11 FA Cups.\r\n\r\nArsenal's success has been particularly consistent: the club has accumulated the second most points in English top-flight football, hold the ongoing record for the longest uninterrupted period in the top flight, and would be placed first in an aggregated league of the entire 20th century. Arsenal is the second side to complete an English top-flight season unbeaten (in the 2003–04 season), playing almost twice as many matches as the previous invincibles Preston North End in the 1888–89 season.\r\n\r\nArsenal was founded in 1886 in Woolwich and in 1893 became the first club from the south of England to join the Football League. In 1913, they moved north across the city to Arsenal Stadium in Highbury. In the 1930s, they won five League Championship titles and two FA Cups. After a lean period in the post-war years they won the League and FA Cup Double, in the 1970–71 season, and in the 1990s and first decade of the 21st century, won two more Doubles and reached the 2006 UEFA Champions League Final. Since neighbouring Tottenham Hotspur, the two clubs have had a fierce rivalry, the North London derby.\r\n\r\nArsenal have one of the highest incomes and largest fanbases in the world. The club was named the fifth most valuable association football club in the world, valued at £1.3 billion in 2014."
            result[0].strTeamBadge shouldEqual "https://www.thesportsdb.com/images/media/team/badge/a1af2i1557005128.png"
            result[0].strTeamJersey shouldEqual "https://www.thesportsdb.com/images/media/team/jersey/2019-133604-Jersey.png"
            result[0].strYoutube shouldEqual "www.youtube.com/user/ArsenalTour"
        }
    }
}
