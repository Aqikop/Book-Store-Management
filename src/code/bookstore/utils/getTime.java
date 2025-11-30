package code.bookstore.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class getTime {
    public String getCurrentTime(){
        LocalDateTime time = LocalDateTime.now();
        String time_to_str = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return time_to_str;
    }
}
