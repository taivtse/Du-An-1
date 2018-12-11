package poly.app.ui.report;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class VeBanReportParameter extends HashMap<String, Object>{

    private final String BACKGROUND_URL = VeBanReportParameter.class.getResource("../icons/ve-ban-background.jpg").getPath();
    public VeBanReportParameter(String filmName, int ticketPrice, String movieFormat, int showScreen, Date showDate, Date showTime, String seatPosition) {
        this.put("FilmName", filmName);
        this.put("TicketPrice", new DecimalFormat("##,###,###").format(ticketPrice));
        this.put("MovieFormat", movieFormat);
        this.put("ShowScreen", showScreen + "");
        this.put("ShowDate", new SimpleDateFormat("dd/MM/yyyy").format(showDate));
        this.put("ShowTime", new SimpleDateFormat("HH:mm:ss").format(showTime));
        this.put("SeatPosition", seatPosition);
        this.put("BackgroundURL", BACKGROUND_URL);
    }
}
