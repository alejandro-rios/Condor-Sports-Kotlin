package com.alejandrorios.teamlist

import com.alejandrorios.core.viewmodels.ViewTeamData
import com.alejandrorios.teamlist.domain.mapper.ViewTeamsMapper
import com.alejandrorios.teamlist.domain.models.TeamData
import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * Created by Alejandro Rios on 2019-10-23
 */
class ViewTeamsMapperTest {

    @Test
    fun `should get view teams from teams`() {
        val mapper = given {
            ViewTeamsMapper
        }

        val result = whenever {
            mapper.map(
                TeamData(
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
                    "",
                    "",
                    "www.youtube.com/user/ArsenalTour"
                )
            )
        }

        then {
            result shouldEqual ViewTeamData(
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
                "",
                "",
                "www.youtube.com/user/ArsenalTour"
            )
        }
    }
}
