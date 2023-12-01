import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5_android.ForecastItem
import com.example.tp5_android.ForecastResponse
import com.example.tp5_android.R
import com.example.tp5_android.elemList
import kotlin.math.roundToInt


class ForecastAdapter(private val context: Context, private val forecastList: ForecastResponse) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.forecast_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecastItem = forecastList.list[position]
        holder.bind(forecastItem)
    }

    override fun getItemCount(): Int {
        return forecastList.list.size
    }

    inner class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val dateTimeTextView: TextView = itemView.findViewById(R.id.textViewDateTime)
        private val temperatureTextView: TextView = itemView.findViewById(R.id.textViewTemperature)
        private val windSpeedTextView: TextView = itemView.findViewById(R.id.textViewWind)

        fun bind(elemList: elemList) {
            dateTimeTextView.text = elemList.dt_txt
            temperatureTextView.text = "${convertKelvinToCelsius(elemList.main.temp)} °C"
            windSpeedTextView.text = "Wind: ${elemList.wind.speed} m/s"

        }
    }
    private fun convertKelvinToCelsius(kelvin: Double): Int {
        return (kelvin - 273.15).roundToInt()
    }
}
