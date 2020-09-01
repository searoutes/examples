This small project shows an example of how to use Searoutes' API to obtain the route geometry between ports on sea. You can find our API documentation page by following [this link](https://developper.searoutes.com). For more info about our API products, you can visit our [website](https://discover.searoutes.com). 

# Getting started

To run this project, you need java 11, the latest version of [gradle](https://gradle.org/install/), and an API key (you can get one from us [here](https://searoutes.typeform.com/to/ZRAoy5)).

```sh
$ git clone <project>
$ cd <project> & gradle build 
$ export SEAROUTES_API_KEY=<your_api_key>
$ gradle run
```

A sample output from the properties of the API response, for a route between Hamburg (DEHAM) and New York (USNYC) is shown below:

```json
{
  "distance": 6656086,
  "mode": "sea",
  "departure": 1598980520971,
  "arrival": 1599551041971,
  "duration": 570521000,
  "speed": 42,
  "areas": {
    "type": "FeatureCollection",
    "features": [
      {
        "type": "Feature",
        "properties": {
          "id": 10808,
          "name": "ECA US Eastcoast",
          "alternatives": []
        },
        "geometry": {
          "type": "Point",
          "coordinates": [
            -73.88756494646312,
            42.16576079204333
          ]
        }
      },
      {
        "type": "Feature",
        "properties": {
          "id": 10810,
          "name": "SECA Europe",
          "alternatives": []
        },
        "geometry": {
          "type": "Point",
          "coordinates": [
            3.3955,
            56.2781
          ]
        }
      },
      {
        "type": "Feature",
        "properties": {
          "id": 11204,
          "name": "Fair Isle",
          "alternatives": []
        },
        "geometry": {
          "type": "Point",
          "coordinates": [
            -1.6193,
            59.7158
          ]
        }
      }
    ]
  },
  "details": [],
  "secaIntersection": 3599939,
  "speedInKts": 22.678,
  "vessel": {
    "imo": 8677225,
    "name": "KSL PRIDE",
    "length": 83,
    "width": 15,
    "maxDraft": 4.2,
    "draft": 4.2
  }
}
```
