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

`GET /api/countries`

=> return JSON with all represented countries
```javascript
{
  id: "FR",
  name: "France"
},
{
  id: "HU",
  name: "Hungary"
}
```

#### Athletes requests

`GET /api/athletes/{id}`

`GET /api/athletes/country={country}`

`GET /api/athletes/sport={sport}`

`GET /api/athletes/name={name}`

#### Response format
##### Application/Json :
```javascript
{
 id: 50,
 name: "Szilágyi",
 surname: "Áron",
 content: "Champion olympique du sabre",
 country: {
    id: "HU",
    name: "Hungary"
  },
 sport: "escrime",
 age: 24,
 descritpion: { },
 image_url: "http://olympic-insa.fr.nf:8083/image/download/51"
}
```

#### Image Request

###### Upload image Base64 encoded + metadata embeded in JSON Format
`POST image/api/upload`

###### Required format :
```javascript
{
  name: "Name",
  descritpion: "metadata string",
  content: "00444040400024+=",
  contentType: "jpg",
}
```
Enjoy ! 

======================
Olympic-INSA Team
