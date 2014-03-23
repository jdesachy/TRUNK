package gps.description.converter;

import java.text.ParseException;

import com.thoughtworks.xstream.converters.ConversionException;

public class DateConverterException extends ConversionException {

	public DateConverterException(String date, ParseException e) {
		super("Impossible de parser la date : " + date, e);
	}

	private static final long serialVersionUID = 1334499110790092709L;

}
