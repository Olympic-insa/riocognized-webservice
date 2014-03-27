Rio'Cognized Webservice
======================

Rio'cognized App RESTFull Webservice. 

## Web GUI
`GET /`
=> Main Page - Add & Delete Athlete

`POST /delete/{id}`
=> Delete Athlete & return /

`GET /images`
 => image admin main page
 
`GET /images/download/{id}`
=> Display image

`GET /images/remove/{id}`
=> Display image

`POST /images/save`
=> POST image using jsp form

## API

`GET /api/athletes`

=> return JSON with all athletes

#### Sports requests

`GET /api/sports`

=> return JSON with all sports

#### Athletes requests

`GET /api/athletes/{id}`

`GET /api/athletes/country={country}`

`GET /api/athletes/sport={sport}`

`GET /api/athletes/name={name}`

#### Response format
##### Application/Json :
[{
id: 14,
name: "Brownlee",
surname: "Alistair",
content: "Champion Olympique",
country: "Royaume-Uni",
sport: "Triathlon",
age: 24,
image_url: "http://olympic-insa.fr.nf:8083/image/download/29",
}]

#### Image Request

###### Upload image Base64 encoded + metadata embeded in JSON Format
`POST image/api/upload`

###### Required format :
[{
name: "Name",
descritpion: "metadata string",
content: "00444040400024+=",
contentType: "jpg",
}]

Enjoy ! 

======================
Olympic-INSA Team
