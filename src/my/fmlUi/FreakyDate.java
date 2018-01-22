package my.fmlUi;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.sql.Date;

/**
 *
 * @author termaat
 */
public class FreakyDate {

        static final int CALENDAR_WIDTH = 7;
	static final int CALENDAR_HEIGHT = 6;
	int[][] calDates = new int[CALENDAR_HEIGHT][CALENDAR_WIDTH];
	int year, month, day, lastDate;
	final int[] lastDateOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // JFMAMJJASOND
	Calendar today = Calendar.getInstance(), cal;

	public void setCurrentDate() { // Sets current calendar data based on
									// getInstance of today
		this.year = this.today.get(1);
		this.month = this.today.get(2);
		this.day = this.today.get(5);
		//initializeCalendarData(this.today);
	}
        
        public Date getActualFOM(int modifier){
            Calendar fom = Calendar.getInstance();
            fom.set(Calendar.DATE, fom.getActualMinimum(Calendar.DATE));
            fom.add(Calendar.MONTH, + modifier);
            java.util.Date firstDayOfMonth = fom.getTime();
            java.sql.Date sqlDate1 = new java.sql.Date(firstDayOfMonth.getTime());
            
            return sqlDate1;
        }
        // returns the end of the month date given a date. also adds or 
        // subtracts based on modifier.  0 retunrs eom current month
        public Date getEOM(int modifier){
            Calendar eom = Calendar.getInstance();
            eom.set(Calendar.DATE, eom.getActualMaximum(Calendar.DATE));
            eom.add(Calendar.MONTH, + modifier);
            java.util.Date lastDayOfMonth = eom.getTime();
            java.sql.Date sqlDate30 = new java.sql.Date(lastDayOfMonth.getTime());
            
            return sqlDate30;
        }
        
        public Date getFOM(int modifier){
            Calendar fom = Calendar.getInstance();
            fom.set(Calendar.DATE, fom.getActualMaximum(Calendar.DATE));
            fom.add(Calendar.MONTH, + modifier);
            java.util.Date firstDayOfMonth = fom.getTime();
            java.sql.Date sqlDate1 = new java.sql.Date(firstDayOfMonth.getTime());
            
            return sqlDate1;
            
        }
        
        // 8 needs to be the modifier and 8, 16, 24, 32...
        public Date getEOW(int modifier) {
            int dayofweek = 0;
        
            //get current day
            Calendar eow = Calendar.getInstance();
            dayofweek = eow.get(Calendar.DAY_OF_WEEK);

            //we want the last day to be monday of the next week so we can
            //return sunday in the list
            int lastdaymodifier = modifier - dayofweek;
        
            //add this number to calender to get the last date of the week
            eow.add(Calendar.DAY_OF_WEEK,  + lastdaymodifier +1);
        
            java.util.Date lastDateOfWeek = eow.getTime();
            java.sql.Date sqlDate7 = new java.sql.Date(lastDateOfWeek.getTime());
            
            return sqlDate7;
        
        }
        
        public Date getFOW(int modifier){
            int dayofweek = 0;
        
            Calendar eow = Calendar.getInstance();
            dayofweek = eow.get(Calendar.DAY_OF_WEEK);
            int lastdaymodifier = modifier - dayofweek;
            eow.add(Calendar.DAY_OF_WEEK,  + lastdaymodifier +1);
     
            // now that we know the last date of the week we can minus 8 to 
            // get the first date of the week
            eow.add(Calendar.DAY_OF_WEEK,  - 8);
        
            java.util.Date firstDateOfWeek = eow.getTime();
            java.sql.Date sqlDate1 = new java.sql.Date(firstDateOfWeek.getTime());
            return sqlDate1;
        }
        
        public void moveMonth(int mon) {
		month += mon;
		if (month > 11)
			while (month > 11) {
				year++;
				month -= 12;
			}
		else if (month < 0)
			while (month < 0) {
				year--;
				month += 12;
			}
		cal = new GregorianCalendar(year, month, day);
		//initializeCalendarData(cal);
	}
}
