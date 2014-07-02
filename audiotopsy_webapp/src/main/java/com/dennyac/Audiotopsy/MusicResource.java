package com.dennyac.Audiotopsy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/music")
public class MusicResource {

	MSDViews msdViews = new MSDViews();

	@GET
	@Path("/yearwisestats")
	@Produces("application/json")
	public String yearWise() {
		return msdViews.getYearWiseStats().toString();
	}

	@GET
	@Path("/yeartoptracks")
	@Produces("application/json")
	public String yearTopTracks(@QueryParam("year") String year) {

		try {
			if (year != null && Integer.parseInt(year) > 1900
					&& Integer.parseInt(year) < 2015) {
				System.out.println("The value of year is " + year);
				return msdViews.getYearTopTracks(year).toString();
			}

		} catch (Exception e) {
			System.out.println("Error encountered in MusicResource"
					+ e.getMessage());
		}
		return null;

	}

}
