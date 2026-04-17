import java.util.Scanner;

/**
 * Represents a date (month, day, and year).
 */
public class Date {
    private String month;
    private int day;
    private int year; // a four digit number.

    public Date() {
        month = "January";
        day = 1;
        year = 1000;
    }

    public Date(int monthInt, int day, int year) {
        setDate(monthInt, day, year);
    }

    public Date(String monthString, int day, int year) {
        setDate(monthString, day, year);
    }

    public Date(int year) {
        setDate(1, 1, year);
    }

    public Date(Date aDate) {
        if (aDate == null) {
            throw new IllegalArgumentException("Date object is null.");
        }
        month = aDate.month;
        day = aDate.day;
        year = aDate.year;
    }

    public void setDate(int monthInt, int day, int year) {
        if (dateOK(monthInt, day, year)) {
            this.month = monthString(monthInt);
            this.day = day;
            this.year = year;
        } else {
            throw new IllegalArgumentException("Invalid date.");
        }
    }

    public void setDate(String monthString, int day, int year) {
        if (dateOK(monthString, day, year)) {
            this.month = monthString;
            this.day = day;
            this.year = year;
        } else {
            throw new IllegalArgumentException("Invalid date.");
        }
    }

    public void setDate(int year) {
        setDate(1, 1, year);
    }

    public void setYear(int year) {
        if ((year < 1000) || (year > 9999)) {
            throw new IllegalArgumentException("Year must be between 1000 and 9999.");
        } else {
            this.year = year;
        }
    }

    public void setMonth(int monthNumber) {
        if ((monthNumber <= 0) || (monthNumber > 12)) {
            throw new IllegalArgumentException("Month must be 1-12.");
        } else {
            month = monthString(monthNumber);
        }
    }

    public void setDay(int day) {
        if ((day <= 0) || (day > 31)) {
            throw new IllegalArgumentException("Day must be between 1 and 31.");
        } else {
            this.day = day;
        }
    }

    public int getMonth() {
        if (month.equals("January"))
            return 1;
        else if (month.equals("February"))
            return 2;
        else if (month.equalsIgnoreCase("March"))
            return 3;
        else if (month.equalsIgnoreCase("April"))
            return 4;
        else if (month.equalsIgnoreCase("May"))
            return 5;
        else if (month.equals("June"))
            return 6;
        else if (month.equalsIgnoreCase("July"))
            return 7;
        else if (month.equalsIgnoreCase("August"))
            return 8;
        else if (month.equalsIgnoreCase("September"))
            return 9;
        else if (month.equalsIgnoreCase("October"))
            return 10;
        else if (month.equals("November"))
            return 11;
        else if (month.equals("December"))
            return 12;
        else {
            throw new IllegalArgumentException("Invalid month name: " + month);
        }
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return (month + " " + day + ", " + year);
    }

    public boolean equals(Date otherDate) {
        if (otherDate == null)
            return false;
        else
            return (month.equals(otherDate.month)) &&
                   (day == otherDate.day) &&
                   (year == otherDate.year);
    }

    public boolean precedes(Date otherDate) {
        return (year < otherDate.year)
                || (year == otherDate.year && getMonth() < otherDate.getMonth())
                || (year == otherDate.year && month.equals(otherDate.month) && day < otherDate.day);
    }

    /**
     * Reads a date from input in format 'Month day year'.
     */
    public void readInput() {
        boolean tryAgain = true;
        Scanner keyboard = new Scanner(System.in);
        while (tryAgain) {
            System.out.println("Enter month, day, and year.");
            System.out.println("Do not use a comma.");
            String monthInput = keyboard.next();
            int dayInput = keyboard.nextInt();
            int yearInput = keyboard.nextInt();
            if (dateOK(monthInput, dayInput, yearInput)) {
                setDate(monthInput, dayInput, yearInput);
                tryAgain = false;
            } else {
                System.out.println("Illegal date. Reenter input.");
            }
        }
    }

    private boolean dateOK(int monthInt, int dayInt, int yearInt) {
        if (yearInt < 1000 || yearInt > 9999) {
            return false;
        }
        if (monthInt < 1 || monthInt > 12) {
            return false;
        }
        int maxDay;
        switch (monthInt) {
            case 2:
                if (isLeapYear(yearInt)) maxDay = 29;
                else maxDay = 28;
                break;
            case 4: case 6: case 9: case 11:
                maxDay = 30;
                break;
            default:
                maxDay = 31;
        }
        return (dayInt >= 1 && dayInt <= maxDay);
    }

    private boolean dateOK(String monthString, int dayInt, int yearInt) {
        if (yearInt < 1000 || yearInt > 9999) {
            return false;
        }
        int monthInt;
        switch (monthString.toLowerCase()) {
            case "january": monthInt = 1; break;
            case "february": monthInt = 2; break;
            case "march": monthInt = 3; break;
            case "april": monthInt = 4; break;
            case "may": monthInt = 5; break;
            case "june": monthInt = 6; break;
            case "july": monthInt = 7; break;
            case "august": monthInt = 8; break;
            case "september": monthInt = 9; break;
            case "october": monthInt = 10; break;
            case "november": monthInt = 11; break;
            case "december": monthInt = 12; break;
            default: return false;
        }
        int maxDay;
        switch (monthInt) {
            case 2:
                if (isLeapYear(yearInt)) maxDay = 29;
                else maxDay = 28;
                break;
            case 4: case 6: case 9: case 11:
                maxDay = 30;
                break;
            default:
                maxDay = 31;
        }
        return (dayInt >= 1 && dayInt <= maxDay);
    }

    private boolean isLeapYear(int year) {
        return ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)));
    }

    private boolean monthOK(String month) {
        return (month.equals("January") || month.equals("February") ||
                month.equals("March") || month.equals("April") ||
                month.equals("May") || month.equals("June") ||
                month.equals("July") || month.equals("August") ||
                month.equals("September") || month.equals("October") ||
                month.equals("November") || month.equals("December"));
    }

    private String monthString(int monthNumber) {
        switch (monthNumber) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default:
                throw new IllegalArgumentException("Invalid month number: " + monthNumber);
        }
    }
}