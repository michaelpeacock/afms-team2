package skyenet.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

@Path("/getenvdata")
@Produces("application/json")
public class GetEnvData {
	
	@GET
	@Produces("application/json")
	public Response selectData() throws JSONException {
		JSONArray measurements = new JSONArray();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// , TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String output = "";

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from mydb.measurements;");
			
			while (rs.next()) {
				JSONObject json = new JSONObject();
				json.put("time", getInt("time", rs));
				json.put("compass_magnetic_direction", getDouble("compass_magnetic_direction", rs));
				json.put("compass_true_direction", getDouble("compass_true_direction", rs));
				json.put("wind_speed", getDouble("wind_speed", rs));
				json.put("crosswind", getDouble("crosswind", rs));
				json.put("headwind", getDouble("headwind", rs));
				json.put("temperature", getDouble("temperature", rs));
				json.put("wind_chill", getDouble("wind_chill", rs));
				json.put("relative_humidity", getDouble("relative_humidity", rs));
				json.put("heat_index", getDouble("heat_index", rs));
				json.put("dew_points", getDouble("dew_points", rs));
				json.put("wet_bulb_temperature", getDouble("wet_bulb_temperature", rs));
				json.put("barometric_pressure", getDouble("barometric_pressure", rs));
				json.put("altitude", getDouble("altitude", rs));
				json.put("density_altitude", getDouble("density_altitude", rs));

				measurements.put(json);
			}
			
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(200).entity(measurements.toString()).build();

	}
	
	private String getDouble(String str, ResultSet rs) {
		try {
				return Double.toString(rs.getDouble(str));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0.0";
	}
	
	private String getInt(String str, ResultSet rs) {
		try {
			return Integer.toString(rs.getInt(str));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	return "0";
	}
}
