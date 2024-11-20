package com.taxi.service;

import java.util.List;

import com.taxi.model.BookingForm;

public interface BookingFormService {
	public BookingForm BookMyData(BookingForm bookingForm);
	public List<BookingForm>readAllBookingsService();
    public void deleteBookingService(int id);
}
