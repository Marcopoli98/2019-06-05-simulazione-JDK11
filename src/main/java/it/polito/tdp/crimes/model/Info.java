package it.polito.tdp.crimes.model;

import com.javadocmd.simplelatlng.LatLng;

public class Info {

	private int id1;
	private LatLng punto;

	public Info(int id1, LatLng punto) {
		super();
		this.id1 = id1;
		this.punto = punto;
	}

	public int getId1() {
		return id1;
	}

	public void setId1(int id1) {
		this.id1 = id1;
	}

	public LatLng getPunto() {
		return punto;
	}

	public void setPunto(LatLng punto) {
		this.punto = punto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id1;
		result = prime * result + ((punto == null) ? 0 : punto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Info other = (Info) obj;
		if (id1 != other.id1)
			return false;
		if (punto == null) {
			if (other.punto != null)
				return false;
		} else if (!punto.equals(other.punto))
			return false;
		return true;
	}

	
	
}
