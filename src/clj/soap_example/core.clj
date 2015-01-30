(ns soap-example.core
  (:import [com.cdyne.ws.weatherws Weather]))

(defn- unpack-temp [t]
  {:low  (-> t .getMorningLow  Integer/parseInt)
   :high (-> t .getDaytimeHigh Integer/parseInt)})

(defn- unpack-pop [p]
  {:night (-> p .getNighttime Integer/parseInt)
   :day   (-> p .getDaytime   Integer/parseInt)})

(defn- unpack-forecast [f]
  {:date          (-> f .getDate .toGregorianCalendar)
   :temperatures  (-> f .getTemperatures unpack-temp)
   :description   (-> f .getDesciption) ; sic
   :precipitation (-> f .getProbabilityOfPrecipiation unpack-pop)})

(defn- result->map [res]
  (let [forecast (-> res
                     .getForecastResult
                     .getForecast
                     (->> (map unpack-forecast)))]
    {:city (.getCity res)
     :state (.getCity res)
     :forecast forecast}))

(defn get-city-forecast-by-zip [zip]
  (let [service (.getWeatherSoap (Weather.))
        zip     (str zip)
        result  (.getCityForecastByZIP service zip)]
    (result->map result)))

;;;; Most city data doesn't seem to be present, these few work as examples.
(defn nyc-weather    [] (get-city-forecast-by-zip 10001))
(defn philly-weather [] (get-city-forecast-by-zip 19107))

