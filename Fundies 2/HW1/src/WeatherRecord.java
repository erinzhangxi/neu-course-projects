import tester.Tester;

// Practice 6
// Problem 11.2 on page 113

class Date {
    int day;
    int month;
    int year;
    
        Date(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }
}

class TemperatureRange {
    int high;
    int low;
    
        TemperatureRange(int high, int low) {
            this.high = high;
            this.low = low;
        }
}

class WeatherRecord {
    Date d;
    TemperatureRange today;
    TemperatureRange normal;
    TemperatureRange record;
    
        WeatherRecord(Date d, TemperatureRange today, TemperatureRange normal,
                TemperatureRange record) {
            this.d = d;
            this.today = today;
            this.normal = normal;
            this.record = record;
        }
        
        /* TEMPLATE
         * FIELDS:
         * ... this.d...      -- Date
         * ... this.today...  -- TemperatureRange
         * ... this.normal...  -- TemperatureRange
         * ... this.record...  -- TemperatureRange
         * 
         *  METHODS:
         *  ... this.withinRange()...  -- boolean
         *  ... this.recordDay()...    -- boolean
         */
        
        
        boolean withinRange() {
            return
                    this.today.low > this.normal.low 
                    &&
                    this.today.high < this.normal.high;
        }

        boolean recordDay(){
            return
                   this.today.low < this.record.low
                   ||
                   this.today.high > this.record.high;
        }

}


class ExamplesWeatherReport {
	WeatherRecord wr1 = new WeatherRecord(new Date(9, 16, 2015), 
			new TemperatureRange(10, 90),
			new TemperatureRange(20, 70),
			new TemperatureRange(30, 85));
	
	boolean testWithinRange(Tester t) {
		return t.checkExpect(this.wr1.withinRange(), true)
				&&
				t.checkExpect(this.wr1.recordDay(), false);
	}
}
