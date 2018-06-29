package demo

import grails.testing.web.controllers.ControllerUnitTest
import org.springframework.dao.DataIntegrityViolationException
import spock.lang.Specification

class DemoControllerSpec extends Specification implements ControllerUnitTest<DemoController> {
    
    void "test save is successful"() {
        given:
        def parkingType = GroovySpy(ParkingType, global: true)
        1 * parkingType.save() >> parkingType

        when:
        controller.create()
        
        then:
        response.text == 'Saved PT'
    }

    void "test save is unsuccessful"() {
        given:
        def parkingType = GroovySpy(ParkingType, global: true)
        1 * parkingType.save() >> null

        when:
        controller.create()

        then:
        response.text == 'Failed To Save PT'
    }

    void 'test save throws DataIntegrityViolationException'() {
        given:
        def parkingType = GroovySpy(ParkingType, global: true)
        1 * parkingType.save() >> { throw new DataIntegrityViolationException('Something Went Wrong') }

        when:
        controller.create()

        then:
        response.text == 'DIVE was thrown [Something Went Wrong]'
    }
}
