package com.example.backend.service.reservation;

import com.example.backend.controller.board.request.SeatRequest;
import com.example.backend.entity.Board;
import com.example.backend.entity.BoardComments;
import com.example.backend.entity.Reservation;
import com.example.backend.repository.reservation.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {


    @Autowired
    private ReservationRepository rsvRepository;


@Transactional
@Override
public List<Reservation> list(String picker) {

    return rsvRepository.findByPicker(picker);

}

    //예약시 등록하기(예약 번호는 자동으로,( 자리번호, 시간(당일), 글쓴이), 멤버번호(조인컬럼))
    @Transactional
    @Override
    public void rsvRegister(Reservation reservation) {

        rsvRepository.save(reservation);

    }

    //userId를 넣으면 그 예약을 조회가능하도록 (= read기능과 비슷)
    @Transactional
    @Override
    public Reservation getBookingRead(Long reservationNo) {//원래 userId넣어야 되는데 생각중
        Optional<Reservation> maybeGetBookingRead = rsvRepository.findById(reservationNo);

        if (maybeGetBookingRead.equals(Optional.empty())) {
            log.info("Can't get BookingRead!");
            return null;
        }

        return maybeGetBookingRead.get();
    }


    //업데이트 된 내용을 넣기
    @Transactional
    @Override
    public void updateModify(Reservation reservation) {


        rsvRepository.save(reservation);

    }

    //삭제
    @Transactional
    @Override
    public void remove(Long reservationNo) {

        rsvRepository.deleteById(reservationNo);
    }
    @Transactional
    @Override
    public List<Reservation> searchIdList(String searchId) {
        return rsvRepository.findIdList(searchId);

    }



    @Transactional //아이디 넣고 리스트 불러올때
    @Override
    public Reservation read(Long checkReservationNo, String searchId) {
        Optional<Reservation> maybeReadBoard = rsvRepository.findById(checkReservationNo);

        if(maybeReadBoard.equals(Optional.empty())){
            log.info("no booking");
        }

        Reservation rsvId = maybeReadBoard.get();

        if(rsvId.getId().equals(searchId)){
            log.info("searchId:"+searchId);
            return rsvId;
        }else if(searchId.equals("manager")) {
            log.info("callBookingList");
            return rsvId;
        }else
            return null;


    }
}
