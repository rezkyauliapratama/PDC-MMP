package android.rezkyauliapratama.com.mmppdc.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Rezky Aulia Pratama on 11/9/18.
 */

interface NetworkApi{

        @GET(EndPoints.dummyLink)
        fun dummyApi(
                @Query("id") leagueId:String?
                ): Single<String>




}