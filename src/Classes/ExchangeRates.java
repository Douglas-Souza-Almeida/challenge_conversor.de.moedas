package Classes;

// Importação da anotação SerializedName da biblioteca Gson
import com.google.gson.annotations.SerializedName;
import java.util.Map;

// Definição do record ExchangeRates para armazenar taxas de câmbio
public record ExchangeRates (
        // Anotação para mapear o campo JSON "base_code" para a variável "base"
        @SerializedName("base_code") String base,

        // Anotação para mapear o campo JSON "time_last_update_utc" para a variável "date"
        @SerializedName("time_last_update_utc") String date,

        // Anotação para mapear o campo JSON "conversion_rates" para a variável "conversionRates"
        @SerializedName("conversion_rates") Map<String, Double> conversionRates){
}