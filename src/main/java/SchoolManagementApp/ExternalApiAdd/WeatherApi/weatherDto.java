package SchoolManagementApp.ExternalApiAdd.WeatherApi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class weatherDto {
    private Current current;

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public class Current {
        private int temperature;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;

        private int feelslike;

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public List<String> getWeatherDescriptions() {
            return weatherDescriptions;
        }

        public void setWeatherDescriptions(List<String> weatherDescriptions) {
            this.weatherDescriptions = weatherDescriptions;
        }

        public int getFeelslike() {
            return feelslike;
        }

        public void setFeelslike(int feelslike) {
            this.feelslike = feelslike;
        }
    }
}
