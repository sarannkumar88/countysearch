# County Suggestions

Get suggested counties based on provided query string. Responds with an array, limited to 5 results, of counties where the county name or state matches the provided query string.

Endpoint URL: http://localhost:3000/suggest

Examples:-
http://localhost:3000/suggest?q=cowl

Response:-
[
    {
        "fips": "20035",
        "state": "KS",
        "name": "Cowley"
    },
    {
        "fips": "53015",
        "state": "WA",
        "name": "Cowlitz"
    }
]

http://localhost:3000/suggest?q=cowlitz, wa
[
    {
        "fips": "53015",
        "state": "WA",
        "name": "Cowlitz"
    }
]

http://localhost:3000/suggest?q=wa

[
    {
        "fips": "53001",
        "state": "WA",
        "name": "Adams"
    },
    {
        "fips": "53003",
        "state": "WA",
        "name": "Asotin"
    },
    {
        "fips": "53005",
        "state": "WA",
        "name": "Benton"
    },
    {
        "fips": "53007",
        "state": "WA",
        "name": "Chelan"
    },
    {
        "fips": "53009",
        "state": "WA",
        "name": "Clallam"
    }
]
