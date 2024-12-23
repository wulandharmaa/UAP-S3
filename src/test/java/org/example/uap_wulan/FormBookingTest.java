package org.example.uap_wulan;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class FormBookingTest {

    private FormBooking formBooking;

    @BeforeEach
    void setUp() {
        formBooking = new FormBooking();
    }

    @Test
    @DisplayName("Test Validasi Nama Tidak Boleh Kosong")
    void testNamaTidakBolehKosong() {
        Exception exception = assertThrows(Exception.class, () -> {
            formBooking.validateBookingData("", "Eksklusif", "1-Jan-2024", "1", "/path/to/image.jpg");
        });

        String expectedMessage = "Nama tidak boleh kosong!";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    @DisplayName("Test Validasi Membership Harus Dipilih")
    void testMembershipHarusDipilih() {
        Exception exception = assertThrows(Exception.class, () -> {
            formBooking.validateBookingData("John Doe", "", "1-Jan-2024", "1", "/path/to/image.jpg");
        });

        String expectedMessage = "Membership harus dipilih!";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    @DisplayName("Test Validasi Tanggal Booking Harus Lengkap")
    void testTanggalBookingHarusLengkap() {
        Exception exception = assertThrows(Exception.class, () -> {
            formBooking.validateBookingData("John Doe", "Eksklusif", "", "1", "/path/to/image.jpg");
        });

        String expectedMessage = "Tanggal Booking harus lengkap!";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

}