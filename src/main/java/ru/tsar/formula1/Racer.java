package ru.tsar.formula1;

import java.time.Duration;

public class Racer {
	
	public String name;
	public String team;
	public Duration bestTime;
	public Integer place;

	public Integer getPlace() {
		return place;
	}

	public void setPlace(Integer place) {
		this.place = place;
	}

	public Racer(String name, String team, Duration bestTime) {
		this.name = name;
		this.team = team;
		this.bestTime = bestTime;
		this.place = 0;
	}

	@Override
	public String toString() {
		return "Racer [name=" + name + ", team=" + team + ", bestTime=" + bestTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bestTime == null) ? 0 : bestTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
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
		Racer other = (Racer) obj;
		if (bestTime == null) {
			if (other.bestTime != null)
				return false;
		} else if (!bestTime.equals(other.bestTime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Duration getBestTime() {
		return bestTime;
	}

	public void setBestTime(Duration bestTime) {
		this.bestTime = bestTime;
	}

}
