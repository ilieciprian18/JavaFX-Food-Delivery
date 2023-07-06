package Classes;

public class DateOfBirth {
    protected int day;
    private int month;
    private int year;

    public DateOfBirth() {
    }

    public DateOfBirth(int day, int month, int year){
        this.day= day;
        this.month =month;
        this.year = year;
    }

    public DateOfBirth(DateOfBirth e){
        this.day = e.day;
        this.month = e.month;
        this.year =e.year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return
                "(day)" + day +
                        "-(month)" + month +
                        "-(year)" + year
                ;
    }



    public boolean equals(Object o){
        if( o == null)
        {
            return false;
        }
        if ( o instanceof DateOfBirth)
        {
            DateOfBirth date = (DateOfBirth) o;

            if((day == date.day) & (month == date.month) & (year == date.year))
            {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }

    }
}
