Rio'Cognized Webservice
======================

Rio'cognized App RESTFull Webservice. 

[lynxlabs.fr.nf][1]

#### Face Recognition API

###### Facial Recognition based on FischerFaces method using OpenCV.
###### Pass full image URL as argument :
`GET recognition/api/detect?url={http://urltoyourimage.co/image.jpg}`
###### Try it!
http://lynxlabs.fr.nf:8083/recognition/api/recognize?url=http://static.guim.co.uk/sys-images/Guardian/About/General/2012/8/6/1344280750667/Alistair-Brownlee-built-a-008.jpg

###### Response
```Javascript
200 OK
{
   precision: 2761.738703045782,
   image: "http://static.guim.co.uk/sys-images/Guardian/About/General/2012/8/6/1344280750667/Alistair-Brownlee-built-a-008.jpg",
   detected: 1,
   athlete: {
      id: 14,
      image_url: "http://olympic-insa.fr.nf:8083/image/download/29",
      name: "Brownlee",
      sport: "triathlon",
      surname: "Alistair",
      country: {
         id: "GB",
         name: "United Kingdom"
      }
   }
}
```
#### Face Detetction API

###### Facial Detection based on Viola & Jones method.
###### Pass full image URL as argument :
`GET recognition/api/detect?url={http://urltoyourimage.co/image.jpg}`
###### Try it!
http://lynxlabs.fr.nf:8083/recognition/api/detect?url=http://www.ouest-france.fr/sites/default/files/styles/image-article-detail/public/2014/04/06/des-primes-scandaleusement-faibles.jpg

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
#### Ads API

###### Get a random ad.

` GET /ad `
###### [Try it -  :coffee:][2]

## Web GUI
###### New web GUI Available !

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
```javascript
200 OK
{
  id: 14,
  name: "Brownlee",
  surname: "Alistair",
  country: 
  {
    id: "GB",
    name: "United Kingdom"
  },
  sport: "triathlon"
  image_url: "http://olympic-insa.fr.nf:8083/image/download/29"
}
```

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

#### Athletes advanced query

`GET /api/athletes/{id}`

`GET /api/athletes/country={country}`

`GET /api/athletes/sport={sport}`

`GET /api/athletes/name={name}`

```
GET /api/athletes?{criteria}={name}

country, char(2) : do you know his sport ?
sport, varchar(50) : do you know his nationality ?
gender, (M/F) : male or female.
skin_color, varchar(50)
fit, varchar(50) : athlete's fit (small, tall, skinny, strong).
race_suit, varchar(50) : athlete's race suit dominant color.
hair_color/hair_length, varchar(50) : athlete's hair color/length.
racing, true/false : is athlete currently racing.

```


#### Response format
##### Application/Json :
```javascript
200 OK
{
  id: 14,
  name: "Brownlee",
  surname: "Alistair",
  content: "Champion Olympique",
  country: 
  {
    id: "GB",
    name: "United Kingdom"
  },
  sport: 
  {
    id: "triathlon"
  },
  timetables: 
  [
    {
      id: 2,
      sport: 
      {       
        id: "triathlon"
      },
      position: 
      {
        id: 3,
        type: "outdoor",
        latitude: -22.9644,
        longitude: -43.1799,
        description: "copacabana beach"
      },
      startDate: "2016-08-01,14:00",
      endDate: "2016-08-01,18:00",
      description: "men triathlon"
      }
  ],
  startDate: "1988-04-23",
  descritpion: 
  {
    fit: "skinny",
    race_suit: "blue",
    color: "white"
  },
  age: 25,
  image_url: "http://olympic-insa.fr.nf:8083/image/download/29"
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


  [1]: http://lynxlabs.fr.nf:8083
  [2]: http://lynxlabs.fr.nf:8083/ad
