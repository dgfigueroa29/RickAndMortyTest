# RickAndMortyTest by Boa Apps

Android App Technical Testing with Kotlin, Compose, Ktor

## Features

- Make a GET request to rickandmortyapi.com to get test data.
- Parse the JSON response and map it to Kotlin objects.
- Create a UI with Jetpack Compose that displays the data in a list.
- Implement MVVM architecture to structure the code appropriately.
- Manage UI states (loading, success, error) efficiently.
- Use of Kotlin coroutines and reactive programming principles.
- Proper handling of states and errors in the UI.
- Application of Clean Architecture.

### Using rickandmortyapi API RESTful

#### Specifically the route GET all location

```cURL
curl --location --globoff 'https://rickandmortyapi.com/api/location'
```

#### Usage/Examples

```json
{
 "info": {
    "count": 126,
    "pages": 7,
    "next": "https://rickandmortyapi.com/api/location?page=2",
    "prev": null
  },
  "results": [
    {
      "id": 1,
      "name": "Earth",
      "type": "Planet",
      "dimension": "Dimension C-137",
      "residents": [
        "https://rickandmortyapi.com/api/character/1",
        "https://rickandmortyapi.com/api/character/2"
      ],
      "url": "https://rickandmortyapi.com/api/location/1",
      "created": "2017-11-10T12:42:04.162Z"
    }
  ]
}
```
