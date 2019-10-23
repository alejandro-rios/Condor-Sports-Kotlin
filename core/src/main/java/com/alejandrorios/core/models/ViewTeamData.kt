package com.alejandrorios.core.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Alejandro Rios on 2019-10-23
 */
@Parcelize
data class ViewTeamData(
    var idTeam: String? = null,
    var strTeam: String? = null,
    var strAlternate: String? = null,
    var intFormedYear: String? = null,
    var strStadium: String? = null,
    var strWebsite: String? = null,
    var strFacebook: String? = null,
    var strInstagram: String? = null,
    var strDescriptionEN: String? = null,
    var strTeamBadge: String? = null,
    var strTeamJersey: String? = null,
    var strYoutube: String? = null
): Parcelable
