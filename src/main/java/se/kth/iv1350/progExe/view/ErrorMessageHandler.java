package se.kth.iv1350.progExe.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class handles the displaying of error messages.
 */
public class ErrorMessageHandler {

    /**
     * Displays the given error message along with the current time.
     *
     * @param message The error message to be displayed.
     */
    public void showErrorMsg(String message) {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append(getCurrentTime());
        errorMessage.append(", ERROR: ");
        errorMessage.append(message);
        System.out.println(errorMessage);
    }

    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(dateTimeFormatter);
    }
}
