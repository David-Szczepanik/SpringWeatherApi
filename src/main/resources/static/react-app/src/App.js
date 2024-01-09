import { useEffect, useState } from "react";
import axios from "axios";

function App() {
  const [weatherData, setWeatherData] = useState([]);
  const [showRoom, setShowRoom] = useState({});
  const [showData, setShowData] = useState(false);
  const [time, setTime] = useState("");
  const [selectedCity, setSelectedCity] = useState("");

  useEffect(() => {
    getData();
    //nastavení intervalu aktualizace dat na co 2 minuty
    const interval = setInterval(() => {
      getData();
    }, 120000); // 120000 ms = 2 minut
    //čištění intervalu po odstranění komponenty
    return () => clearInterval(interval);
  }, []);

  useEffect(() => {
    // Aktualizace showRoom při změně weatherData nebo selectedCity
    if (selectedCity !== "" && weatherData.length > 0) {
      const cityData = weatherData.find(
        (item) => item.location === selectedCity
      );
      if (cityData) {
        setShowRoom(cityData);
        setShowData(true);
      }
    }
  }, [weatherData, selectedCity]);

  const getCurrentTime = () => {
    const now = new Date();
    return now.toLocaleTimeString();
  };

  const getData = () => {
    axios
      .get("https://pocasiunas.azurewebsites.net/weather")
      .then(function (response) {
        //console.log(response.data);
        setWeatherData(response.data);
        setTime(getCurrentTime);
      })
      .catch(function (error) {
        const alertMessage = `API nedostupné: ${error}`;
        alert(alertMessage);
        console.error("There was an error!", error);
      });
  };

  const handleChange = (e) => {
    let city = e.target.value;
    if (city !== "label") {
      setShowRoom(weatherData.find((item) => item.location === city));
      setShowData(true);
      setSelectedCity(city);
    } else {
      setShowData(false);
      setSelectedCity("");
    }
  };

  return (
    <div className="container">
      <div className="row my-5 text-center">
        <h1 className="display-3">WeatherApp</h1>
      </div>
      <div className="row my-5">
        <div className="col-md-6 mx-auto">
          <select className="form-select" onChange={handleChange}>
            <option value="label">vyberte město</option>
            {weatherData.map((item, index) => (
              <option key={index} value={item.location}>
                {item.location}
              </option>
            ))}
          </select>
        </div>
      </div>
      <div className="row my-2">
        <p className="col-md-6 mx-auto">Aktualizováno: {time}</p>
      </div>
      <div className="row my-5">
        <div
          className={showData ? "col-md-6 mx-auto" : "col-md-6 mx-auto d-none"}
        >
          <h2 className="display-6">{showRoom.location}</h2>
          <p>Čas: {showRoom.timestamp}</p>
          <p>Teplota: {showRoom.temp_celsius}°C</p>
          <p>Rel. vlhkost: {showRoom.rel_humidity} %</p>
          <p>Rychlost větru: {showRoom.windSpeed_mps} mi/h</p>
          <p>Směr větru: {showRoom.windDirection}</p>
          <p>Počasí: {showRoom.weatherDescription}</p>
        </div>
      </div>
    </div>
  );
}

export default App;
