package com.example.violi.therostersqliteversion10;



public class Roster {

    int id;
    String firstName, lastName, position, birthDay, team;

    public Roster(int id, String firstName, String lastName, String birthDay, String team, String position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.position = position;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getPosition() {
        return position;
    }

    public String getTeam() {
        return team;
    }


}
