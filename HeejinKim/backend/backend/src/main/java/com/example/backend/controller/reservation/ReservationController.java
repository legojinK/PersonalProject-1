package com.example.backend.controller.reservation;

import com.example.backend.controller.board.request.SeatNowRequest;
import com.example.backend.entity.Reservation;
import com.example.backend.service.reservation.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reservation")
@CrossOrigin(origins ="http://localhost:8080", allowedHeaders = "*")
public class ReservationController {

    @Autowired
    private ReservationService rsvService;

    //1)등록 2) 수정 (업데이트) 3) 리스트 가져오기 4) 읽기 5) 삭제

    //디비로 확정 정보 넣기--원래 @RequestParam("time") String time,@RequestParam("seatNumber") String seatNumber 들어가는데 다 넣음
    @PostMapping("/finish")
    public void rsvRegister(@Validated @RequestBody Reservation reservation) {

        log.info("rsvRegister()");

        rsvService.rsvRegister(reservation);
    }


    //리스트 가져오기---전체 자리 읽어오기
    @GetMapping("/reservationList/{picker}")
    public List<Reservation> reservationListList( @PathVariable("picker") String picker) throws Exception {
        log.info("reservationListList()");

        return rsvService.list(picker);
    }



    //Id로 예약내역 조회---userId를 넣어야하는데 일단 보류
    //일단 레포지토리나 서비스에서 id로 찾기를 하기 때문에 여기서 예약 번호를 넣음
    @GetMapping("/checkBooking/{reservationNo}")
    public Reservation getBookingRead(@PathVariable("reservationNo") Long reservationNo) {

        log.info("getBookingRead()");
        return rsvService.getBookingRead(reservationNo);
    }

    //업데이트 사항 넣기
    @PutMapping("/update")
    public Reservation updateModify(@Validated @RequestBody Reservation reservation) {
        log.info("updateModify(): " + reservation);

        //reservation.setReservationNo(reservationNo);
        rsvService.updateModify(reservation);

        return reservation;
    }


    //삭제
    @DeleteMapping("/{reservationNo}")
    public void reservationRemove(
            @PathVariable("reservationNo") Long reservationNo) {
        log.info("reservationRemove()");

        rsvService.remove(reservationNo);
    }


    //업데이트된 정보 넣기
    @PutMapping("/finish/{reservationNo}")
    public Reservation updateReservationModify(
            @PathVariable("reservationNo") Long reservationNo,
            @RequestBody Reservation reservation) {
        log.info("updateReservation(): " + reservation);

        reservation.setReservationNo(reservationNo);
        rsvService.updateModify(reservation);

        return reservation;
    }
    // 내 예약 정보 불러올때
    @GetMapping("/list/{searchId}")
    public List<Reservation> ReservationPerList (@PathVariable("searchId") String searchId) {
        log.info("reservationPerList()");


        return rsvService.searchIdList(searchId);
    }

    @RequestMapping(value = "/read",  method = RequestMethod.GET)
    public Reservation bookingReadBoard (@RequestParam(value = "checkReservationNo", required = false) Long checkReservationNo,
                                         @RequestParam(value = "searchId", required = false) String searchId) {


        log.info("checkReservationNo:" +checkReservationNo + "id:" +searchId);

        return rsvService.read(checkReservationNo,searchId);
    }
}



