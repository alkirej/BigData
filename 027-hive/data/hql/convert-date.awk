BEGIN { 
    OFS="|"; 
    FS="|"; 
    split("Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec", MON );
} 

function twoCharMonth( monthNum ) {
    if ( monthNum < 10 ) {
        return "0" monthNum;
    } else {
        return monthNum;
    }
}

function convertMonth( month ) {
    for (i=1; i<=12; i++) {
        if ( month == MON[i] ) {
            return i;
        }
    }

    return 0;
}

{ 
    split( $3, dmy, "-")
    monNum = convertMonth( MON[dmy[2]] )

    $3 = dmy[3] "-" twoCharMonth(monNum) "-" dmy[1]

    print 
}


