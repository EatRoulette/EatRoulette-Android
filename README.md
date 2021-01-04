# EatRoulette-Android



## Configuration

Write in the **local.properties** file the following line :

```properties
eatrouletteApiBaseUrl=YOUR_API_BASE_URL
googleBaseApiUrl=https://maps.googleapis.com/maps/api/
gooleKey=YOUR_API_KEY
```

In the file app/src/debug/res/values/google_maps_api.xml, replace **YOUR_API_KEY** by your key :

```xml
<string name="google_maps_key" ... >YOUR_API_KEY</string>
```

