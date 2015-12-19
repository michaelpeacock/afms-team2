package skyenet.com;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;



@Path("/postenvdata")
public class PostEnvData {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postEnvData(InputStream incomingData) {
		String stmt = new String();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();}

		StringBuilder envBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line= null;
			while ((line = in.readLine()) != null) {
				envBuilder.append(line);
				JSONObject json = new JSONObject(envBuilder.toString());

				//String stmt = "insert into mydb.measurements (";
				stmt = "insert into mydb.measurements (";
				String dest = "";
				String src = "";

				Vector<String>keys = new Vector<String>();
				Vector<String>values = new Vector<String>();


				for (int i = 0; i < json.names().length(); i++)
				{
					keys.addElement(json.names().getString(i));
					values.addElement( json.get(json.names().getString(i)).toString());
				}

				for (int i = 0 ; i < keys.size(); i++)
				{
					stmt += keys.get(i);
					if (i != keys.size()-1){
						stmt += ", ";
					}
				}

				stmt += ") values (";

				for (int i = 0 ; i < values.size(); i++)
				{
					stmt += values.get(i);
					if (i != values.size()-1){
						stmt += ", ";
					}
				}

				stmt += ")";

				/*for (int i=0; i < )

			stmt += "time, ";
			stmt += "compass_magnetic_direction, ";
			stmt += "compass_true_direction, ";
			stmt += "wind_speed, ";
			stmt += "crosswind, ";
			stmt += "headwind, ";
			stmt += "temperature, ";
			stmt += "wind_chill, ";
			stmt += "relative_humidity, ";
			stmt += "heat_index, ";
			stmt += "dew_points, ";
			stmt += "wet_bulb_temperature, ";
			stmt += "barometric_pressure, ";
			stmt += "altitude, ";
			stmt += "density_altitude) ";
			stmt += " values (";
			stmt += json.getString("time") + ", ";
			stmt += json.getString("compass_magnetic_direction") + ", ";
			stmt += json.getString("compass_true_direction") + ", ";
			stmt += json.getString("wind_speed") + ", ";
			stmt += json.getString("crosswind") + ", ";
			stmt += json.getString("headwind") + ", ";
			stmt += json.getString("temperature") + ", ";
			stmt += json.getString("wind_chill") + ", ";
			stmt += json.getString("relative_humidity") + ", ";
			stmt += json.getString("heat_index") + ", ";
			stmt += json.getString("dew_points") + ", ";
			stmt += json.getString("wet_bulb_temperature") + ", ";
			stmt += json.getString("barometric_pressure") + ", ";
			stmt += json.getString("altitude") + ", ";
			stmt += json.getString("density_altitude") + ")";*/

				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "");
					PreparedStatement ps = con.prepareStatement(stmt);
					int result = ps.executeUpdate();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - " + stmt); 
		}
		System.out.println("Data Received: " + envBuilder.toString());

		return Response.status(200).entity(envBuilder.toString()).build();
	}
}
