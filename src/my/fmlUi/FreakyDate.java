package my.fmlUi;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.sql.Date;
import java.util.Locale;

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
        final String[] MonthText = {"January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", 
                "November", "December"};
        
	Calendar today = Calendar.getInstance(), cal;

	public void setCurrentDate() { // Sets current calendar data based on
									// getInstance of today
		this.year = this.today.get(1);
		this.month = this.today.get(2);
		this.day = this.today.get(5);
		//initializeCalendarData(this.today);
	}
        
        public String getMonthDesc(int modifier){
            Calendar m_desc = Calendar.getInstance();
            m_desc.add(Calendar.MONTH, + modifier);
            String m_text = m_desc.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
            return m_text;
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
            // System.out.println("eow - day of week today is " + dayofweek);
            //we want the last day to be monday of the next week so we can
            //return sunday in the list
            int lastdaymodifier = modifier - dayofweek;
            //System.out.println("eow - lastdaymodifier = " + modifier + " - " + dayofweek);
            //add this number to calender to get the last date of the week
            eow.add(Calendar.DAY_OF_WEEK,  + lastdaymodifier +1);
            //System.out.println("eow - adding " + lastdaymodifier + " + 1 to get to Sunday");
            java.util.Date lastDateOfWeek = eow.getTime();
            java.sql.Date sqlDate7 = new java.sql.Date(lastDateOfWeek.getTime());
            //System.out.println("eow - returning " + sqlDate7);
            return sqlDate7;
        
        }
        
        public Date getFOW(int modifier){
            int dayofweek = 0;
        
            Calendar fow = Calendar.getInstance();
            dayofweek = fow.get(Calendar.DAY_OF_WEEK);
            //System.out.println("fow - day of week today is " + dayofweek);
            int lastdaymodifier = modifier - dayofweek;
            //System.out.println("fow - lastdaymodifier = " + modifier + " - " + dayofweek);
            fow.add(Calendar.DAY_OF_WEEK,  + lastdaymodifier +1);
            //System.out.println("fow - adding " + lastdaymodifier + " + 1 to get to Sunday");
            // now that we know the last date of the week we can minus 8 to 
            // get the first date of the week
            fow.add(Calendar.DAY_OF_WEEK,  - 7);
            //System.out.println("fow - now subtracting 7 to get day before Monday");
            java.util.Date firstDateOfWeek = fow.getTime();
            java.sql.Date sqlDate1 = new java.sql.Date(firstDateOfWeek.getTime());
            //System.out.println("fow SQL - returning " + sqlDate1);
            return sqlDate1;
        }
        
        public Date getActualFOW(int modifier){
            int dayofweek = 0;
        
            Calendar fow = Calendar.getInstance();
            dayofweek = fow.get(Calendar.DAY_OF_WEEK);
            //System.out.println("fow actual - day of week today is " + dayofweek);
            int lastdaymodifier = modifier - dayofweek;
            //System.out.println("fow actual - lastdaymodifier = " + modifier + " - " + dayofweek);
            fow.add(Calendar.DAY_OF_WEEK,  + lastdaymodifier +1);
            //System.out.println("fow actual - adding " + lastdaymodifier + " + 1 to get to Sunday");
            // now that we know the last date of the week we can minus 8 to 
            // get the first date of the week
            fow.add(Calendar.DAY_OF_WEEK,  - 6);
            //System.out.println("fow actual - now subtracting 6 to get Monday");
            java.util.Date firstDateOfWeek = fow.getTime();
            java.sql.Date sqlDate1 = new java.sql.Date(firstDateOfWeek.getTime());
            //System.out.println("fow actual  - returning " + sqlDate1);
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
