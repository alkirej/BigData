
class Constants:
    HTTP_GET: str = "GET"

    HEADERS: dict = {
        'x-rapidapi-host': "api-nba-v1.p.rapidapi.com",
        'x-rapidapi-key': "1f01528fb6msh561539a23d5f12ep158231jsna853de0e02d6"
        }

    # URL_TEAM_INFO: str    = "https://api-nba-v1.p.rapidapi.com/teams/teamId/"
    # URL_PLAYER_LIST: str  = "http://data.nba.net/10s/prod/v1/2019/players.json"

    URL_GAMES_ON_DAY: str = "https://api-nba-v1.p.rapidapi.com/games/date/"

    RSS_URLS: list = [ "https://api.foxsports.com/v1/rss?tag=nba",
                       "https://www.espn.com/espn/rss/nba/news"
                     ]

    GAME_DATA_DIR: str = "data/"
    RSS_DATA_DIR: str  = "rss_data/"