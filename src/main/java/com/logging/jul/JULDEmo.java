package com.logging.jul;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;

/**
 * The Logger you create is actually a hierarchy of Loggers, and a . (dot) in the hierarchy indicates a level in the hierarchy.
 * So if you get a Logger for the com.example key, this Logger is a child of the com Logger and the com Logger is child of the Logger for the empty String.
 * You can configure the main logger and this affects all its children
 *
 * <li>
 *     The following lists the Log Levels in descending order:

         SEVERE (highest)

         WARNING

         INFO

         CONFIG

         FINE

         FINER

         FINEST
 * </li>
 * In addition to that you also have the levels OFF and ALL to turn the logging off or to log everything.
 * For example, the following code sets the logger to the info level, which means all messages with severe, warning and info will be logged.
 * LOGGER.setLevel(Level.INFO);
 *
 * <p> Handler </p>
 * Each logger can have access to several handlers.
 * The handler receives the log message from the logger and exports it to a certain target.
 * A handler can be turned off with the setLevel(Level.OFF) method and turned on with setLevel() method.
 * You have several standard handlers. The following list gives some examples.
 * ConsoleHandler: Write the log message to console
 * FileHandler: Writes the log message to file
 *
 * <p> Formatter </p>
 *  Each handler’s output can be configured with a formatter
 *  Available formatter :
 *   <li>SimpleFormatter: Generate all messages as text</li>
 *   <li>XMLFormatter: Generates XML output for the log messages</li>
 *   You can also build your own formatter.
 *
 *   The following is an example of a formatter which will create HTML output.
 package com.vogella.logger;

 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.logging.Formatter;
 import java.util.logging.Handler;
 import java.util.logging.Level;
 import java.util.logging.LogRecord;

 // this custom formatter formats parts of a log record to a single line
 class MyHtmlFormatter extends Formatter {
 // this method is called for every log records
 public String format(LogRecord rec) {
 StringBuffer buf = new StringBuffer(1000);
 buf.append("<tr>\n");

 // colorize any levels >= WARNING in red
 if (rec.getLevel().intValue() >= Level.WARNING.intValue()) {
 buf.append("\t<td style=\"color:red\">");
 buf.append("<b>");
 buf.append(rec.getLevel());
 buf.append("</b>");
 } else {
 buf.append("\t<td>");
 buf.append(rec.getLevel());
 }

 buf.append("</td>\n");
 buf.append("\t<td>");
 buf.append(calcDate(rec.getMillis()));
 buf.append("</td>\n");
 buf.append("\t<td>");
 buf.append(formatMessage(rec));
 buf.append("</td>\n");
 buf.append("</tr>\n");

 return buf.toString();
 }

 private String calcDate(long millisecs) {
 SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
 Date resultdate = new Date(millisecs);
 return date_format.format(resultdate);
 }

 // this method is called just after the handler using this
 // formatter is created
 public String getHead(Handler h) {
 return "<!DOCTYPE html>\n<head>\n<style>\n"
 + "table { width: 100% }\n"
 + "th { font:bold 10pt Tahoma; }\n"
 + "td { font:normal 10pt Tahoma; }\n"
 + "h1 {font:normal 11pt Tahoma;}\n"
 + "</style>\n"
 + "</head>\n"
 + "<body>\n"
 + "<h1>" + (new Date()) + "</h1>\n"
 + "<table border=\"0\" cellpadding=\"5\" cellspacing=\"3\">\n"
 + "<tr align=\"left\">\n"
 + "\t<th style=\"width:10%\">Loglevel</th>\n"
 + "\t<th style=\"width:15%\">Time</th>\n"
 + "\t<th style=\"width:75%\">Log Message</th>\n"
 + "</tr>\n";
 }

 // this method is called just after the handler using this
 // formatter is closed
 public String getTail(Handler h) {
 return "</table>\n</body>\n</html>";
 }
 }

 *<p> Log Manager </p>
 * The log manager is responsible for creating and managing the logger and the maintenance of the configuration.
 * We could set the logging level for a package, or even a set of packages, by calling the LogManager.setLevel(String name, Level level) method.
 * So, for example, we could set the logging level of all loggers to Level.FINE by making this call:
 * LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.FINE);
 *
 * @author Hamza Ouni
 */
public class JULDEmo {


    public static void main(String[] args) {
        Inner i = new Inner();
        i.file_hundler();
        i.test_jul();
    }

    static class Inner{
        java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(this.getClass().getName());
        void test_jul() {
            //Log levels INFO and higher will be automatically written to the console. so fine log level will not be written in the console
            logger.info("This is an info message");
            logger.fine("Here is a debug message"); // == DEBUG
            logger.severe("This is an error message"); // == ERROR

        }

        void file_hundler() {
            ConsoleHandler consoleHandler = null;
            try {
                consoleHandler = new ConsoleHandler();
                logger.addHandler(consoleHandler);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
