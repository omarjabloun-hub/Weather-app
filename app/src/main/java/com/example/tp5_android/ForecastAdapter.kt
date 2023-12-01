import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5_android.ForecastItem
import com.example.tp5_android.R


class ForecastAdapter(private val context: Context, private val forecastList: List<ForecastItem>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.forecast_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecastItem = forecastList[position]
        holder.bind(forecastItem)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    inner class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val dateTimeTextView: TextView = itemView.findViewById(R.id.textViewDateTime)
        private val temperatureTextView: TextView = itemView.findViewById(R.id.textViewTemperature)
        private val humidityTextView: TextView = itemView.findViewById(R.id.humidityTextView)
        private val windSpeedTextView: TextView = itemView.findViewById(R.id.textViewWind)

        fun bind(forecastItem: ForecastItem) {
            dateTimeTextView.text = forecastItem.dateTime
            temperatureTextView.text = "${forecastItem.temperature} °C"
            humidityTextView.text = "Humidity: ${forecastItem.humidity}%"
            windSpeedTextView.text = "Wind: ${forecastItem.windSpeed} m/s"


        }
    }
}
