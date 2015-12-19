package skyenet.com;

/**
 * Created by OmegaSupreme on 12/18/2015.
 */
public class Measurements {
	public String[] field_list = {"air_density", "air_flow", "altitude", "absolute_pressure", "air_velocity", "barometric_pressure", "concrete_temperature", 
			"crosswind", "density_altitude", "dew_points", "time", "evaporation_rate", "globe_temperature", "heat_index", "humidity_ratio", "headwind", 
			"compass_magnetic_direction", "mean_radiant_temperature", "natural_wet_bulb_temperature", "relative_air_density", "relative_humidity", "SC", 
			"temperature", "compass_true_direction", "thermal_work_limit", "wet_bulb_temperature", "wet_bulb_globe_temperature", "wind_chill", "latitude", 
			"longitude"};
	
    private int id;
    private double air_density;
    private double air_flow;
    private double altitude;
    private double absolute_pressure;
    private double air_velocity;
    private double barometric_pressure;
    private double concrete_temperature;
    private double crosswind;
    private double density_altitude;
    private double dew_points;
    private long time;
    private double evaporation_rate;
    private double globe_temperature;
    private double heat_index;
    private double humidity_ratio;
    private double headwind;
    private double compass_magnetic_direction;
    private String mean_radiant_temperature;
    private double natural_wet_bulb_temperature;
    private double relative_air_density;
    private double relative_humidity;
    private String SC;
    private double temperature;
    private double compass_true_direction;
    private double thermal_work_limit;
    private double wet_bulb_temperature;
    private double wet_bulb_globe_temperature;
    private double wind_chill;
    private double wind_speed;
    private double latitude;
    private double longitude;

    public Measurements() {
    	
    }
    
    public Measurements(int id, double air_density, double air_flow, double altitude, double absolute_pressure,
            double air_velocity, double barometric_pressure, double concrete_temperature, double crosswind,
            double density_altitude, double dew_points, long time, double evaporation_rate, double globe_temperature,
            double heat_index, double humidity_ratio, double headwind, double compass_magnetic_direction,
            String mean_radiant_temperature, double natural_wet_bulb_temperature, double relative_air_density,
            double relative_humidity, String SC, double temperature, double compass_true_direction,
            double thermal_work_limit, double wet_bulb_temperature, double wet_bulb_globe_temperature,
            double wind_chill, double wind_speed, double latitude, double longitude){

        this.id = id;
        this.air_density = air_density;
        this.air_flow = air_flow;
        this.altitude = altitude;
        this.absolute_pressure = absolute_pressure;
        this.air_velocity = air_velocity;
        this.barometric_pressure = barometric_pressure;
        this.concrete_temperature = concrete_temperature;
        this.crosswind = crosswind;
        this.density_altitude = density_altitude;
        this.dew_points = dew_points;
        this.time = time;
        this.evaporation_rate = evaporation_rate;
        this.globe_temperature = globe_temperature;
        this.heat_index = heat_index;
        this.humidity_ratio = humidity_ratio;
        this.headwind = headwind;
        this.compass_magnetic_direction = compass_magnetic_direction;
        this.mean_radiant_temperature = mean_radiant_temperature;
        this.natural_wet_bulb_temperature = natural_wet_bulb_temperature;
        this.relative_air_density = relative_air_density;
        this.relative_humidity = relative_humidity;
        this.SC = SC;
        this.temperature = temperature;
        this.compass_true_direction = compass_true_direction;
        this.thermal_work_limit = thermal_work_limit;
        this.wet_bulb_temperature = wet_bulb_temperature;
        this.wet_bulb_globe_temperature = wet_bulb_globe_temperature;
        this.wind_chill = wind_chill;
        this.wind_speed = wind_speed;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public String getObjectType(Object obj) {
    	if (obj instanceof Double) {
    		return "double";
    	}
    	else if (obj instanceof Integer) {
    		return "int";
    	}
    	else if (obj instanceof String) {
    		return "String";
    	}
    	
    	return "TypeNotFound";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAir_density() {
        return air_density;
    }

    public void setAir_density(double air_density) {
        this.air_density = air_density;
    }

    public double getAir_flow() {
        return air_flow;
    }

    public void setAir_flow(double air_flow) {
        this.air_flow = air_flow;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getAbsolute_pressure() {
        return absolute_pressure;
    }

    public void setAbsolute_pressure(double absolute_pressure) {
        this.absolute_pressure = absolute_pressure;
    }

    public double getAir_velocity() {
        return air_velocity;
    }

    public void setAir_velocity(double air_velocity) {
        this.air_velocity = air_velocity;
    }

    public double getBarometric_pressure() {
        return barometric_pressure;
    }

    public void setBarometric_pressure(double barometric_pressure) {
        this.barometric_pressure = barometric_pressure;
    }

    public double getConcrete_temperature() {
        return concrete_temperature;
    }

    public void setConcrete_temperature(double concrete_temperature) {
        this.concrete_temperature = concrete_temperature;
    }

    public double getCrosswind() {
        return crosswind;
    }

    public void setCrosswind(double crosswind) {
        this.crosswind = crosswind;
    }

    public double getDensity_altitude() {
        return density_altitude;
    }

    public void setDensity_altitude(double density_altitude) {
        this.density_altitude = density_altitude;
    }

    public double getDew_points() {
        return dew_points;
    }

    public void setDew_points(double dew_points) {
        this.dew_points = dew_points;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getEvaporation_rate() {
        return evaporation_rate;
    }

    public void setEvaporation_rate(double evaporation_rate) {
        this.evaporation_rate = evaporation_rate;
    }

    public double getGlobe_temperature() {
        return globe_temperature;
    }

    public void setGlobe_temperature(double globe_temperature) {
        this.globe_temperature = globe_temperature;
    }

    public double getHeat_index() {
        return heat_index;
    }

    public void setHeat_index(double heat_index) {
        this.heat_index = heat_index;
    }

    public double getHumidity_ratio() {
        return humidity_ratio;
    }

    public void setHumidity_ratio(double humidity_ratio) {
        this.humidity_ratio = humidity_ratio;
    }

    public double getHeadwind() {
        return headwind;
    }

    public void setHeadwind(double headwind) {
        this.headwind = headwind;
    }

    public double getCompass_magnetic_direction() {
        return compass_magnetic_direction;
    }

    public void setCompass_magnetic_direction(double compass_magnetic_direction) {
        this.compass_magnetic_direction = compass_magnetic_direction;
    }

    public String getMean_radiant_temperature() {
        return mean_radiant_temperature;
    }

    public void setMean_radiant_temperature(String mean_radiant_temperature) {
        this.mean_radiant_temperature = mean_radiant_temperature;
    }

    public double getNatural_wet_bulb_temperature() {
        return natural_wet_bulb_temperature;
    }

    public void setNatural_wet_bulb_temperature(double natural_wet_bulb_temperature) {
        this.natural_wet_bulb_temperature = natural_wet_bulb_temperature;
    }

    public double getRelative_air_density() {
        return relative_air_density;
    }

    public void setRelative_air_density(double relative_air_density) {
        this.relative_air_density = relative_air_density;
    }

    public double getRelative_humidity() {
        return relative_humidity;
    }

    public void setRelative_humidity(double relative_humidity) {
        this.relative_humidity = relative_humidity;
    }

    public String getSC() {
        return SC;
    }

    public void setSC(String SC) {
        this.SC = SC;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getCompass_true_direction() {
        return compass_true_direction;
    }

    public void setCompass_true_direction(double compass_true_direction) {
        this.compass_true_direction = compass_true_direction;
    }

    public double getThermal_work_limit() {
        return thermal_work_limit;
    }
    
    public void setThermal_work_limit(double thermal_work_limit) {
        this.thermal_work_limit = thermal_work_limit;
    }

    public double getWet_bulb_temperature() {
        return wet_bulb_temperature;
    }

    public void setWet_bulb_temperature(double wet_bulb_temperature) {
        this.wet_bulb_temperature = wet_bulb_temperature;
    }

    public double getWet_bulb_globe_temperature() {
        return wet_bulb_globe_temperature;
    }

    public void setWet_bulb_globe_temperature(double wet_bulb_globe_temperature) {
        this.wet_bulb_globe_temperature = wet_bulb_globe_temperature;
    }

    public double getWind_chill() {
        return wind_chill;
    }

    public void setWind_chill(double wind_chill) {
        this.wind_chill = wind_chill;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}