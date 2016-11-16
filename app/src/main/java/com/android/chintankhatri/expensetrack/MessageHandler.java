package com.android.chintankhatri.expensetrack;

import android.telephony.SmsManager;

import com.android.chintankhatri.expensetrack.dao.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by sruthimaddineni on 11/15/16.
 */

public class MessageHandler {
    private static final int[] dateField = {Calendar.DATE, Calendar.MINUTE, Calendar.HOUR};
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd-hh:mm");
    private static final String[] banks = {"Chase", "Discover", "Wells Fargo"};
    private static final String[] locations = {"Walmart apache", "CVS Mill", "NY Pizza Mill", "Target Rio Salado", "Red robin Rio Salado"};
    private static final String phoneNum = "";
    private static String generateMessage(final String bank,
                                          final String date,
                                          final String location,
                                          final double amount)  {
        return bank + ": You've spent " + amount + " at " + location + " on " + date;
    }

    private static void sendMessage(final String msg)  {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNum, null, msg, null, null);
    }

    public static Message parseMessage(final String message)    {
        String[] tokens = message.split(" ");

        final Message msg = new Message();
        msg.setAmount(Double.parseDouble(tokens[3]));
        try {
            msg.setTimestamp(dateFormatter.parse(tokens[7]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        msg.setLocation(tokens[5]);
        // TODO - get coordinates of the location
        // msg.setCoordinates();
        msg.setBank(tokens[0]);
        return msg;
    }

    public static void generateMessages(final int count)  {
        Random r = new Random();
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < count; i++)   {
            cal.add(dateField[Math.abs(r.nextInt()%3)], -1*Math.abs(r.nextInt() % 30));
            final String msg = generateMessage(
                    banks[Math.abs(r.nextInt()%3)],
                    dateFormatter.format(cal.getTime()),
                    locations[Math.abs(r.nextInt() % 5)], (Math.abs(r.nextInt())%10000)/100);
            System.out.println(msg);
            sendMessage(msg);
        }
    }
}
