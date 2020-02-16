package net.alkire.capstone

import com.mashape.unirest.http.{HttpResponse, JsonNode, Unirest}
import org.json.JSONArray

object Test {
    def getTeamInfo( id: Int ): String = {
        val response: HttpResponse[String] = Unirest.get("https://api-nba-v1.p.rapidapi.com/teams/teamId/" + id  )
                .header("x-rapidapi-host", "api-nba-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "1f01528fb6msh561539a23d5f12ep158231jsna853de0e02d6")
                .asString();
        response.getBody
    }
    
    
    def getGameInfo(): String = {
        val response: HttpResponse[String] = Unirest.get("https://api-nba-v1.p.rapidapi.com/games/seasonYear/2019" )
                .header("x-rapidapi-host", "api-nba-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "1f01528fb6msh561539a23d5f12ep158231jsna853de0e02d6")
                .asString();
        response.getBody
    }
    
    def main( args: Array[ String ] ): Unit = {
        println( getGameInfo )
    }
}
