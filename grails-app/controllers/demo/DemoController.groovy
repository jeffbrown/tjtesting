package demo

import org.springframework.dao.DataIntegrityViolationException

class DemoController {

    def create() {
        ParkingType pt = new ParkingType()

        if (pt.save()) {
            render "Saved PT"
        } else {
            render "Failed To Save PT"
        }
    }

    def handleDataIntegrityException(DataIntegrityViolationException e) {
        render "DIVE was thrown [${e.message}]"
    }
}
