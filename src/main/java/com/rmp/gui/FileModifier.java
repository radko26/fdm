package main.java.com.rmp.gui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FileModifier {

	public void modify(String newDateString, File file) throws IOException {
		BasicFileAttributeView attrs = Files
				.getFileAttributeView(Paths.get(file.getAbsolutePath()),
						BasicFileAttributeView.class);

		try {
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
			Date newDate = format.parse(newDateString);
			Date oldCreationDate = new Date(attrs.readAttributes()
					.creationTime().toMillis());
			Calendar oldDate = Calendar.getInstance();
			oldDate.setTime(oldCreationDate);

			Calendar modifiedDate = Calendar.getInstance();
			modifiedDate.setTime(newDate);
			modifiedDate.set(Calendar.HOUR_OF_DAY,
					oldDate.get(Calendar.HOUR_OF_DAY));
			modifiedDate.set(Calendar.MINUTE, oldDate.get(Calendar.MINUTE));

			FileTime modifiedCreationDate = FileTime.fromMillis(modifiedDate
					.getTimeInMillis());

			attrs.setTimes(modifiedCreationDate, modifiedCreationDate,
					modifiedCreationDate);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
