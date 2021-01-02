package fr.esgi.eatroulette.connected.restaurant.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import fr.esgi.eatroulette.R

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var restaurant: String? = null
    private var lat: Double? = null
    private var lng: Double? = null

    companion object {
        fun navigateTo(context: Context, restaurantName: String, lat: Double, lng: Double) {
            val intent = Intent(context, MapsActivity::class.java)
            intent.putExtra("restaurant", restaurantName)
            intent.putExtra("lat", lat)
            intent.putExtra("lng", lng)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        restaurant = intent?.getSerializableExtra("restaurant") as String?
        lat = intent?.getDoubleExtra("lat", 0.0)
        lng = intent?.getDoubleExtra("lng", 0.0)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val restaurantMarker = this.lat?.let { this.lng?.let { it1 -> LatLng(it, it1) } }
        mMap.addMarker(restaurantMarker?.let {
            MarkerOptions().position(it).title(this.restaurant)
        })
        mMap.moveCamera(CameraUpdateFactory.newLatLng(restaurantMarker))
        mMap.setMinZoomPreference(15f)
        mMap.setMaxZoomPreference(20f)
    }
}