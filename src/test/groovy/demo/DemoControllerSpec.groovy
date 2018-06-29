package demo

import grails.testing.web.controllers.ControllerUnitTest
import org.springframework.dao.DataIntegrityViolationException
import spock.lang.Specification

class DemoControllerSpec extends Specification implements ControllerUnitTest<DemoController> {
    
    void "test save is successful"() {
        given:
        def foo = GroovySpy(ParkingType, global: true)
        1 * foo.save() >> foo

        when:
        controller.create()
        
        then:
        response.text == 'Saved PT'
    }

    void "test save is unsuccessful"() {
        given:
        def foo = GroovySpy(ParkingType, global: true)
        1 * foo.save() >> null

        when:
        controller.create()

        then:
        response.text == 'Failed To Save PT'
    }

    void 'test save throws DataIntegrityViolationException'() {
        given:
        def foo = GroovySpy(ParkingType, global: true)
        1 * foo.save() >> { throw new DataIntegrityViolationException('Something Went Wrong') }

        when:
        controller.create()

        then:
        response.text == 'DIVE was thrown [Something Went Wrong]'
    }
}
