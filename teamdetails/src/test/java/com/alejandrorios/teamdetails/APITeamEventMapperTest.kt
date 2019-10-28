package com.alejandrorios.teamdetails

import com.alejandrorios.teamdetails.data.entities.APITeamEventData
import com.alejandrorios.teamdetails.data.mapper.APITeamEventMapper
import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * Created by Alejandro Rios on 2019-10-26
 */
class APITeamEventMapperTest {

    @Test
    fun `should get a teamEventData from APITeamEventData`() {
        val apiTeamEventSource = given {
            APITeamEventData(
                "Liverpool",
                "Tottenham",
                "2019-10-27",
                "133602",
                "133616"
            )
        }

        val result = whenever {
            APITeamEventMapper.map(apiTeamEventSource)
        }

        then {
            result.strHomeTeam shouldEqual "Liverpool"
            result.strAwayTeam shouldEqual "Tottenham"
            result.dateEvent shouldEqual "2019-10-27"
            result.idHomeTeam shouldEqual "133602"
            result.idAwayTeam shouldEqual "133616"
        }
    }
}
