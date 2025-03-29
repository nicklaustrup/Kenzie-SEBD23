package com.kenzie.hotel.activity;

import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.kenzie.hotel.dao.ReservationDao;
import com.kenzie.hotel.dao.models.UpdatedReservation;
import com.kenzie.hotel.metrics.MetricsConstants;
import com.kenzie.hotel.metrics.MetricsPublisher;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.inject.Inject;

/**
 * Handles requests to modify a reservation.
 */
public class ModifyReservationActivity {

    private ReservationDao reservationDao;
    private MetricsPublisher metricsPublisher;

    /**
     * Construct ModifyReservationActivity.
     * @param reservationDao Dao used for modify reservations.
     * @param metricsPublisher MetricsPublisher used to publish metrics.
     */
    @Inject
    public ModifyReservationActivity(ReservationDao reservationDao, MetricsPublisher metricsPublisher) {
        this.reservationDao = reservationDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Modifies the given reservation.
     * @param reservationId Id to modify reservations for
     * @param checkInDate modified check in date
     * @param numberOfNights modified number of nights
     * @return UpdatedReservation that includes the old reservation and the updated reservation details.
     */
    public UpdatedReservation handleRequest(final String reservationId, final ZonedDateTime checkInDate,
                                            final Integer numberOfNights) {


        UpdatedReservation updatedReservation = reservationDao.modifyReservation(reservationId, checkInDate,
            numberOfNights);

        BigDecimal costDifference = updatedReservation.getModifiedReservation().getTotalCost().subtract(updatedReservation.getOriginalReservation().getTotalCost());


        metricsPublisher.addMetric(MetricsConstants.REVENUE, costDifference.doubleValue(), StandardUnit.None);
        metricsPublisher.addMetric(MetricsConstants.MODIFICATIONS_COUNT, 1, StandardUnit.Count);


        return updatedReservation;
    }
}
