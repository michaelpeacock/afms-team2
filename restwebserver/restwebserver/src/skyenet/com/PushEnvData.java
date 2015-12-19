package skyenet.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/pushenvdata")
public class PushEnvData {
/*
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response insertTestData() {
		return Response.status(200)
				.entity("Test1")
				.build();
	}
	*/
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response insertData(@QueryParam("air_density") double air_density, 
			@QueryParam("air_flow") double air_flow,
			@QueryParam("altitude") double altitude, 
			@QueryParam("absolute_pressure") double absolute_pressure,
			@QueryParam("air_velocity") double air_velocity,
			@QueryParam("barometric_pressure") double barometric_pressure,
			@QueryParam("concrete_temperature") double concrete_temperature, 
			@QueryParam("crosswind") double crosswind,
			@QueryParam("density_altitude") double density_altitude, 
			@QueryParam("dew_points") double dew_points,
			@QueryParam("time") int time, 
			@QueryParam("evaporation_rate") double evaporation_rate,
			@QueryParam("globe_temperature") double globe_temperature, 
			@QueryParam("heat_index") double heat_index,
			@QueryParam("humidity_ratio") double humidity_ratio, 
			@QueryParam("headwind") double headwind,
			@QueryParam("compass_magnetic_direction") double compass_magnetic_direction,
			@QueryParam("mean_radiant_temperature") String mean_radiant_temperature,
			@QueryParam("natural_wet_bulb_temperature") double natural_wet_bulb_temperature,
			@QueryParam("relative_air_density") double relative_air_density,
			@QueryParam("relative_humidity") double relative_humidity, 
			@QueryParam("SC") String SC,
			@QueryParam("wind_speed") double wind_speed,
			@QueryParam("temperature") double temperature,
			@QueryParam("compass_true_direction") double compass_true_direction,
			@QueryParam("thermal_work_limit") double thermal_work_limit,
			@QueryParam("wet_bulb_temperature") double wet_bulb_temperature,
			@QueryParam("wet_bulb_globe_temperature") double wet_bulb_globe_temperature,
			@QueryParam("wind_chill") double wind_chill, 
			@QueryParam("latitude") double latitude,
			@QueryParam("longitude") double longitude) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// , TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String statementStr = "insert into mydb.measurements ";
		statementStr += "(wind_speed, air_density, air_flow, altitude, absolute_pressure, air_velocity, barometric_pressure, concrete_temperature, crosswind, density_altitude, dew_points, ";
		statementStr += "time, evaporation_rate, globe_temperature, heat_index, humidity_ratio, headwind, compass_magnetic_direction, mean_radiant_temperature, natural_wet_bulb_temperature, ";
		statementStr += "relative_air_density, relative_humidity, SC, temperature, compass_true_direction, thermal_work_limit, wet_bulb_temperature, wet_bulb_globe_temperature, wind_chill, latitude, longitude) ";
		statementStr += "values ";
		statementStr += "( ";
		statementStr += "" + wind_speed + ", ";
		statementStr += "" + air_density + ", ";
		statementStr += "" + air_flow + ", ";
		statementStr += "" + altitude + ", ";
		statementStr += "" + absolute_pressure + ", ";
		statementStr += "" + air_velocity + ", ";
		statementStr += "" + barometric_pressure + ", ";
		statementStr += "" + concrete_temperature + ", ";
		statementStr += "" + crosswind + ", ";
		statementStr += "" + density_altitude + ", ";
		statementStr += "" + dew_points + ", ";
		statementStr += "" + time + ", ";
		statementStr += "" + evaporation_rate + ", ";
		statementStr += "" + globe_temperature + ", ";
		statementStr += "" + heat_index + ", ";
		statementStr += "" + humidity_ratio + ", ";
		statementStr += "" + headwind + ", ";
		statementStr += "" + compass_magnetic_direction + ", ";
		statementStr += "\"" + mean_radiant_temperature + "\", ";
		statementStr += "" + natural_wet_bulb_temperature + ", ";
		statementStr += "" + relative_air_density + ", ";
		statementStr += "" + relative_humidity + ", ";
		statementStr += "\"" + SC + "\", ";
		statementStr += "" + temperature + ", ";
		statementStr += "" + compass_true_direction + ", ";
		statementStr += "" + thermal_work_limit + ", ";
		statementStr += "" + wet_bulb_temperature + ", ";
		statementStr += "" + wet_bulb_globe_temperature + ", ";
		statementStr += "" + wind_chill + ", ";
		statementStr += "" + latitude + ", ";
		statementStr += "" + longitude + "); ";
		
		System.out.println(statementStr);
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "");
			PreparedStatement ps = con.prepareStatement(statementStr);
			int result = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// , TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(200)
				.entity("Data pushed to DB")
				.build();
	}
}
