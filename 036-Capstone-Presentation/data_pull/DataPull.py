"""

"""
import datetime

import requests
import json
from requests.models import Response

from Constants import Constants

"""
def build_simple_api_call( url: str, id: int ) -> str:
    return url + str(id)

def get_team_info( id: int ) -> str:
    team_info_url: str = build_simple_api_call( Constants.URL_TEAM_INFO, id )
    response: Response = requests.request( Constants.HTTP_GET, team_info_url, headers=Constants.HEADERS )
    return json.dumps( response.json() )
"""

# ROUTINES TO READ GAME DATA FROM API AND SAVE IT TO DISK
def build_date_api_call( url: str, year: int, month: int, day: int ) -> str:
    # ASSERTIONS ABOUT INPUT
    assert( year < 2100 )
    assert( year > 0 )
    assert( year < 100 or year > 1948 )

    assert( month > 0 )
    assert( month < 13 )

    assert( day > 0 )
    assert( day < 32 )

    # TURN YEAR INTO A STRING
    year_str: str = str(year)
    if ( year < 99 ):
        year_str = "20" + year_str

    # TURN MONTH INTO A STRING
    month_str: str = str(month)
    if ( month < 10 ):
        month_str = "0" + month_str

    # TURN DAY INTO A STRING
    day_str: str = str(day)
    if ( day < 10 ):
        day_str = "0" + day_str

    date_str: str = year_str + "-" + month_str + "-" + day_str
    return url + date_str

def get_games_for( year: int, month: int, day: int ):
    games_by_day_url: str = build_date_api_call( Constants.URL_GAMES_ON_DAY, year, month, day )
    response: Response = requests.request( Constants.HTTP_GET, games_by_day_url, headers=Constants.HEADERS)
    return json.dumps( response.json() )

def get_yesterdays_games():
    yes: datetime = datetime.datetime.today() - datetime.timedelta(days=1)
    return get_games_for( yes.year, yes.month, yes.day )

def save_yesterdays_games_to_disk( file_contents: str ):
    yes: datetime = datetime.datetime.today() - datetime.timedelta(days=1)
    filename: str = build_date_api_call( Constants.GAME_DATA_DIR, yes.year, yes.month, yes.day )
    file = open( filename, "w+" )
    file.write(file_contents)
    file.close()

# ROUTINES TO READ AN RSS FILE OFF THE WEB AND SAVE IT TO DISK
def read_rss_url( url: str ):
    response: Response = requests.request( Constants.HTTP_GET, url )
    return response.text

def save_rss_to_disk( info: str, url: str, idx: int ):
    yes: datetime = datetime.datetime.today()
    filename: str = build_date_api_call( Constants.RSS_DATA_DIR, yes.year, yes.month, yes.day ) +\
                                    "_" + str(idx) +\
                                    ".rss"
    
    file = open( filename, "w+" )
    file.write( info )
    file.close()


print( " ---- Begin  ---- " )
print( " -- RSS Feeds  -- " )
count: int = 0
for url in Constants.RSS_URLS:
    count += 1
    text: str = read_rss_url( url )
    save_rss_to_disk( text, url, count )
    print( text )
    print( "\n" )

# GET DATA FOR YESTERDAY'S GAMES
print( " -- Game Data  -- " )
"""
output: str = get_yesterdays_games()
save_yesterdays_games_to_disk(output)
"""

# READ FROM RSS FEEDS
print( " --- Complete --- " )
