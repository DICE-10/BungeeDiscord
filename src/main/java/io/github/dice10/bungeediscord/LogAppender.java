package io.github.dice10.bungeediscord;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogAppender extends AbstractAppender {
    SimpleDateFormat formatter;
    // your variables

    public LogAppender() {
        // do your calculations here before starting to capture
        super("MyLogAppender", null, null);
        System.out.println("super");
        start();
    }

    @Override
    public void append(LogEvent event) {
        // if you don`t make it immutable, than you may have some unexpected behaviours
        LogEvent log = event.toImmutable();
        System.out.println("Log");
        formatter = new SimpleDateFormat("HH:mm:ss");

        // do what you have to do with the log

        // you can get only the log message like this:
        String message = log.getMessage().getFormattedMessage();

        // and you can construct your whole log message like this:
        message = "[" +formatter.format(new Date(event.getTimeMillis())) + " " + event.getLevel().toString() + "] " + message;
        System.out.println(message);
    }

}

