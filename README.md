Rio'Cognized Webservice
======================

Rio'cognized App RESTFull Webservice. 

[lynxlabs.fr.nf][1]

#### Face Detetction API

###### Facial Detection based on Viola & Jones method.
###### Pass full image URL as argument :
`GET recognition/api/detect?url={http://urltoyourimage.co/image.jpg}`
###### Try it!
http://lynxlabs.fr.nf:8085/recognition/api/detect?url=http://www.ouest-france.fr/sites/default/files/styles/image-article-detail/public/2014/04/06/des-primes-scandaleusement-faibles.jpg

###### Response
```Javascript
200 OK
{
  result: "http://lynxlabs.fr.nf/opencv/image.jpg" //result image
  detected: 2 //number of face detected
  haar: haar.xml //Haar Classifier used for detection
  url: "http://urlToYouImage.co/yourImage.png"

}
```

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
  name: "Name", // not required
  description: "metadata string", // not required
  content: "00444040400024+=", // base64 format - required
  contentType: "image/jpeg" , //MIME Type - required (image/*)
}
```
###### Response
```Javascript
201 Created
{
   id: 86
  name: null
  description: "metadata string"
  filename: "riocognized-86.png"
  contentType: "image/png"
  created: "04/01/2014 16:53:29"
  athlete: null
  extension: "png"
}
```
######Error Handling (invalid JSON, no content, invalid content)
```Javascript
415 Unsupported Media Type
{
  message: "INVALID_OR_EMPTY_CONTENT"
}
```
Enjoy ! 

======================
Olympic-INSA Team

[1] http://lynxlabs.fr.nf:8083


  [1]: http://lynxlabs.fr.nf:8083
